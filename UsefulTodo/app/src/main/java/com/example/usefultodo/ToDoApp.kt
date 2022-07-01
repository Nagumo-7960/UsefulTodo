package com.example.usefultodo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usefultodo.ui.theme.UsefulTodoTheme

@Composable
fun ToDoApp() {
    val navController = rememberNavController()

    UsefulTodoTheme {
        NavHost(navController = navController, startDestination = "main") {
            // ルート名mainが指定されたときに表示するもの
            composable("main") {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
            // 作成画面
            composable("create") {
            }
            // 詳細画面
            composable("detail/{todoId}") {
            }
            // 編集画面
            composable("edit/{todoId}") {
            }
        }
    }
}
