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


class UserViewModel(private val repository: TypiCodeRepository) : ViewModel() {

    private val _itemState = MutableLiveData<UiState<List<User>>>()

    val itemState: LiveData<UiState<List<User>>> = _itemState

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _itemState.postValue(UiState.Error("exception handler: $e"))
    }

    fun getUsers() {
        viewModelScope.launch(exceptionHandler) {
            _itemState.postValue(UiState.Loading)
            val response = fetchUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    _itemState.postValue(UiState.Success(response.body()!!))
                }
            }
        }
    }

    private suspend fun fetchUsers(): Response<List<User>> {
        return withContext(Dispatchers.IO) {
            repository.getUsers()
        }
    }
}