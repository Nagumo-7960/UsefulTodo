package com.example.usefultodo.repository.todo

import com.example.usefultodo.model.todo.ToDo
import com.example.usefultodo.model.todo.ToDoDAO
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val dao: ToDoDAO
) : ToDoRepository {
    override suspend fun create(title: String, detail: String): ToDo {
        val todo = ToDo(
            title = title,
            detail = detail,
            created = System.currentTimeMillis(),
            modified = System.currentTimeMillis(),
        )
        dao.create(todo)
        return todo
    }
}