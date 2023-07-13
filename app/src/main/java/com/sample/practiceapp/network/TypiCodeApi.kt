package com.sample.practiceapp.network

import androidx.lifecycle.LiveData
import com.sample.practiceapp.model.TodoItem
import com.sample.practiceapp.model.User
import retrofit2.Response
import retrofit2.http.GET

interface TypiCodeApi {

    @GET("/todos")
    suspend fun getToDos(): Response<List<TodoItem>>

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}