package com.example.usefultodo.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usefultodo.R
import com.example.usefultodo.model.todo.ToDo
import com.example.usefultodo.repository.todo.ToDoRepository
import java.util.concurrent.Flow

@Composable
fun CreateToDoScreen(
    navController: NavController,
    viewModel: CreateToDoViewModel,
) {
    val scaffoldState = rememberScaffoldState()

    val title = rememberSaveable { mutableStateOf("") }
    val detail = rememberSaveable { mutableStateOf("") }

    val errorMessage = viewModel.errorMessage.collectAsState()
    val done = viewModel.done.collectAsState()

    if (errorMessage.value.isNotEmpty()) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage.value
            )
            viewModel.errorMessage.value = ""
        }
    }

    if (done.value) {
        // 再コンポーズ時にもう一度実行されたら困る
        viewModel.done.value = false
        navController.popBackStack()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CreateTopBar(navController) {
                viewModel.save(title.value, detail.value)
            }
        },
    ) {
        CreateToDoBody(title, detail)
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
        actions = {
            IconButton(onClick = save) {
                Icon(Icons.Filled.Done, "Save")
            }
        }
    )
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

