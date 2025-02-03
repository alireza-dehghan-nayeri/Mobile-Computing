package com.example.mobile_computing.ui.conversation_app

import androidx.compose.ui.graphics.vector.ImageVector

sealed class ConversationBottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    data object Profile : ConversationBottomNavItem(
        route = ProfileDestination.route,
        icon = ProfileDestination.icon,
        title = ProfileDestination.title
    )

    data object Conversation : ConversationBottomNavItem(
        route = ConversationsDestination.route,
        icon = ConversationsDestination.icon,
        title = ConversationsDestination.title
    )

}

