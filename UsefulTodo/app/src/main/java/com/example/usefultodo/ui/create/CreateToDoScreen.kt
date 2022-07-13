package com.example.usefultodo.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.usefultodo.R

@Composable
fun CreateToDoScreen(
    navController: NavController,
    viewModel: CreateToDoViewModel,
) {
    val title = rememberSaveable { mutableStateOf("") }
    val detail = rememberSaveable { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CreateTopBar(navController) {
                // 実際の処理はViewModelにやらせる
                viewModel.save(title.value, detail.value)
            }
        },
    ) {
        CreateToDoBody(title, detail)
    }
}

@Composable
fun CreateToDoBody(
    title: MutableState<String>,
    detail: MutableState<String>,
) {
    Column {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text(stringResource(id = R.string.todo_title)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        TextField(
            value = detail.value,
            onValueChange = { detail.value = it },
            label = { Text(stringResource(id = R.string.todo_detail)) },
            modifier = Modifier
                .weight(1.0f, true)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun CreateTopBar(navController: NavController, save: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        },
        title = {
            Text(stringResource(id = R.string.create_todo))
        },
        // アクションとして追加
        actions = {
            // タップされたときの処理は親で決める
            IconButton(onClick = save) {
                Icon(Icons.Filled.Done, "Save")
            }
        }
    )
}