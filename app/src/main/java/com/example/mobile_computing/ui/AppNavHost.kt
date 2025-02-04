package com.example.mobile_computing.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = TodoList.route, modifier = modifier) {

        composable(route = TodoList.route) {
            TodoListScreen(
                onAddTodoClicked = {
                    navController.navigate(AddTodo.route)
                },
                onEditTodoClicked = {
                    navController.navigate("${EditTodo.route}/${it}")
                })
        }

        composable(route = AddTodo.route) {
            AddTodoScreen(onSaveClicked = {
                navController.navigateUp()
            })
        }

        composable(
            route = EditTodo.routeWithArgs,
            arguments = EditTodo.arguments
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt(EditTodo.todoIdArg)
            todoId?.let {
                EditTodoScreen(
                    todoId = todoId,
                    onSaveClicked = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
