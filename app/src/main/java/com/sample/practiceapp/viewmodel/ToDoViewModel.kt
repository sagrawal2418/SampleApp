package com.sample.practiceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.practiceapp.UiState
import com.sample.practiceapp.model.TodoItem
import com.sample.practiceapp.model.User
import com.sample.practiceapp.repository.TypiCodeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class ToDoViewModel(private val repository: TypiCodeRepository) : ViewModel() {

    private val _itemState = MutableLiveData<UiState<List<TodoItem>>>()

    val itemState: LiveData<UiState<List<TodoItem>>> = _itemState

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _itemState.postValue(UiState.Error("exception handler: $e"))
    }

    fun getToDos() {
        viewModelScope.launch(exceptionHandler) {
            _itemState.postValue(UiState.Loading)
            val response = fetchToDos()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    _itemState.postValue(UiState.Success(response.body()!!))
                }
            }
        }
    }

    private suspend fun fetchToDos(): Response<List<TodoItem>> {
        return withContext(Dispatchers.IO) {
            repository.getToDos()
        }
    }
}