package com.example.mobile_computing.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobile_computing.data.TodoEntity

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTodoScreen(onSaveClicked: () -> Unit, viewModel: TodoViewModel = hiltViewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var loadImage by remember { mutableStateOf(false) }

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
            value = imageUrl,
            onValueChange = {
                imageUrl = it
                loadImage = false
            },
            label = { Text("Image Url") })

        Spacer(modifier = Modifier.height(16.dp))

        if (loadImage) {
            TodoImage(imageUrl)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loadImage = true
            }
        ) {
            Text("Load Image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank() ) {
                    viewModel.addTodo(TodoEntity(title = title, description = description, imageUrl = imageUrl))
                    onSaveClicked()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
