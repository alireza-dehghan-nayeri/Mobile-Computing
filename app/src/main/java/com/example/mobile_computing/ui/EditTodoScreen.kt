package com.example.mobile_computing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobile_computing.data.TodoEntity

@Composable
fun EditTodoScreen(
    todoId: Int,
    onSaveClicked: () -> Unit,
    viewModel: TodoViewModel = hiltViewModel()
) {

    val todo by viewModel.editingTodo.collectAsState()

    LaunchedEffect(todoId) {
        viewModel.getTodoById(todoId)
    }

    var title by remember { mutableStateOf(todo?.title ?: "") }
    var description by remember { mutableStateOf(todo?.description ?: "") }
    var imageUrl by remember { mutableStateOf(todo?.imageUrl ?: "") }
    var inputImageUrl by remember { mutableStateOf(todo?.imageUrl ?: "") }


    LaunchedEffect(todo) {
        title = todo?.title ?: ""
        description = todo?.description ?: ""
        imageUrl = todo?.imageUrl ?: ""
        inputImageUrl = todo?.imageUrl ?: ""
    }


    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputImageUrl,
            onValueChange = {
                inputImageUrl = it
            },
            label = { Text("Image Url") })
        Spacer(modifier = Modifier.height(16.dp))
        TodoImage(imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                imageUrl = inputImageUrl
            }
        ) {
            Text("Load Image")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    viewModel.addTodo(
                        TodoEntity(
                            id = todoId,
                            title = title,
                            description = description,
                            imageUrl = imageUrl,
                            isCompleted = todo?.isCompleted ?: false
                        )
                    )
                    onSaveClicked()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}