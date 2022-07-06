package com.example.usefultodo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.usefultodo.ui.create.CreateToDoScreen
import com.example.usefultodo.ui.create.CreateToDoViewModel
import com.example.usefultodo.ui.detail.ToDoDetailScreen
import com.example.usefultodo.ui.detail.ToDoDetailViewModel
import com.example.usefultodo.ui.edit.EditToDoScreen
import com.example.usefultodo.ui.edit.EditToDoViewModel
import com.example.usefultodo.ui.main.MainScreen
import com.example.usefultodo.ui.main.MainViewModel
import com.example.usefultodo.ui.theme.UsefulTodoTheme

@Composable
fun ToDoApp() {
    val navController = rememberNavController()

    UsefulTodoTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, viewModel = viewModel)
            }
            composable("create") {
                val viewModel = hiltViewModel<CreateToDoViewModel>()
                CreateToDoScreen(navController = navController, viewModel = viewModel)
            }
            composable(
                "detail/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val viewModel = hiltViewModel<ToDoDetailViewModel>()
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
                ToDoDetailScreen(
                    navController = navController,
                    viewModel = viewModel,
                    todoId = todoId
                )
            }
            composable(
                "edit/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val viewModel = hiltViewModel<EditToDoViewModel>()
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
                EditToDoScreen(
                    navController = navController,
                    viewModel = viewModel,
                    todoId = todoId,
                )
            }
        }
    }
}
