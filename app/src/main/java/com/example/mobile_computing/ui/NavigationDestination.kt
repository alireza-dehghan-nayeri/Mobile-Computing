package com.example.mobile_computing.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavigationDestination {
    val route: String
}

data object TodoList : NavigationDestination {
    override val route = "todo_list"
}

data object AddTodo : NavigationDestination {
    override val route = "add_todo"
}

data object EditTodo : NavigationDestination {
    override val route = "edit_todo"
    const val todoIdArg = "todo_id"
    val routeWithArgs = "$route/{$todoIdArg}"
    val arguments = listOf(
        navArgument(todoIdArg) { type = NavType.IntType }
    )
}