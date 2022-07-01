package com.example.usefultodo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usefultodo.ui.create.CreateToDoScreen
import com.example.usefultodo.ui.detail.ToDoDetailScreen
import com.example.usefultodo.ui.edit.EditToDoScreen
import com.example.usefultodo.ui.main.MainScreen
import com.example.usefultodo.ui.theme.UsefulTodoTheme

@Composable
fun ToDoApp() {
    val navController = rememberNavController()

    UsefulTodoTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(navController = navController)
            }
            composable("create") {
                CreateToDoScreen(navController = navController)
            }
            composable("detail/{todoId}") { backStackEntry ->
                val todoId = backStackEntry.arguments?.getString("todoId")?.toInt() ?: 0
                ToDoDetailScreen(navController = navController, todoId = todoId)
            }
            composable("edit/{todoId}") { backStackEntry ->
                val todoId = backStackEntry.arguments?.getString("todoId")?.toInt() ?: 0
                EditToDoScreen(navController = navController, todoId = todoId)
            }
        }
    }
}
