package com.sample.practiceapp.model

data class TodoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)