package com.example.mobile_computing.ui.conversation_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface ConversationAppNavigationDestination {
    val route: String
    val icon: ImageVector
    val title: String
}

data object ProfileDestination : ConversationAppNavigationDestination {
    override val route: String = "profile"
    override val icon: ImageVector = Icons.Filled.Person
    override val title: String = "Profile"
}

data object ConversationsDestination : ConversationAppNavigationDestination {
    override val route: String = "conversations"
    override val icon: ImageVector = Icons.Filled.Email
    override val title: String = "Conversations"
}



