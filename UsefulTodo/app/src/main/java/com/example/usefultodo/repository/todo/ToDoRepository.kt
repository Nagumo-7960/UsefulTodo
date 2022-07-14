package com.example.usefultodo.repository.todo

import com.example.usefultodo.model.todo.ToDo

interface ToDoRepository {
    suspend fun create(title: String, detail: String): ToDo
}