package com.sample.practiceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.practiceapp.UiState
import com.sample.practiceapp.model.Photo
import com.sample.practiceapp.repository.TypiCodeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class PhotoViewModel(private val repository: TypiCodeRepository) : ViewModel() {

    private val _itemState = MutableLiveData<UiState<List<Photo>>>()

    val itemState: LiveData<UiState<List<Photo>>> = _itemState

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _itemState.postValue(UiState.Error("exception handler: $e"))
    }

    fun getPhotos() {
        viewModelScope.launch(exceptionHandler) {
            _itemState.postValue(UiState.Loading)
            val response = fetchPhotos()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    _itemState.postValue(UiState.Success(response.body()!!))
                }
            }
        }
    }

    private suspend fun fetchPhotos(): Response<List<Photo>> {
        return withContext(Dispatchers.IO) {
            repository.getPhotos()
        }
    }
}