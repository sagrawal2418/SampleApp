package com.sample.practiceapp.repository

import com.sample.practiceapp.model.TodoItem
import com.sample.practiceapp.model.User
import com.sample.practiceapp.network.TypiCodeApi
import retrofit2.Response

class TypiCodeRepository(private val api: TypiCodeApi) {

    suspend fun getToDos(): Response<List<TodoItem>> {
        return api.getToDos()
    }

    suspend fun getUsers(): Response<List<User>> {
        return api.getUsers()
    }

}
