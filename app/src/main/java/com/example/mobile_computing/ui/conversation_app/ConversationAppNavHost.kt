package com.example.mobile_computing.ui.conversation_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ConversationAppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = ConversationsDestination.route,
        modifier = modifier
    ) {

        composable(route = ProfileDestination.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ConversationsDestination.route)
            }
            val parentViewModel = hiltViewModel<ConversationAppViewModel>(parentEntry)
            ProfileScreen(parentViewModel)
        }

        composable(route = ConversationsDestination.route) {
            val viewModel = hiltViewModel<ConversationAppViewModel>()
            ConversationScreen(viewModel)
        }

    }
}