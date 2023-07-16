package com.sample.practiceapp.repository

import com.sample.practiceapp.model.Photo
import com.sample.practiceapp.model.TodoItem
import com.sample.practiceapp.model.User
import retrofit2.Response

interface TypiCodeRepository {

    suspend fun getToDos(): Response<List<TodoItem>>
    suspend fun getUsers(): Response<List<User>>
    suspend fun getPhotos(): Response<List<Photo>>
}