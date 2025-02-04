package com.example.mobile_computing.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_computing.data.TodoDao
import com.example.mobile_computing.data.TodoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val dao: TodoDao) : ViewModel() {

    private val _editingTodo = MutableStateFlow<TodoEntity?>(null)
    val editingTodo: StateFlow<TodoEntity?> = _editingTodo

    val todos: StateFlow<List<TodoEntity>> =
        dao.getTodos().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTodo(todo: TodoEntity) {
        viewModelScope.launch {
            dao.insert(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            dao.delete(todo)
        }
    }

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            dao.getTodoById(id).collect {
                _editingTodo.value = it
            }
        }
    }
}