package com.example.mobile_computing.ui.car_app

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavigationDestination {
    val route: String
}

data object Home : NavigationDestination {
    override val route = "home"
}

data object CarList : NavigationDestination {
    override val route = "car_list"
    const val brandIdArg = "brand_id"
    val routeWithArgs = "$route/{$brandIdArg}"
    val arguments = listOf(
        navArgument(brandIdArg) { type = NavType.StringType }
    )

}

data object CarDetail : NavigationDestination {
    override val route = "car_detail"
    const val carIdArg = "car_id"
    val routeWithArgs = "$route/{$carIdArg}"
    val arguments = listOf(
        navArgument(carIdArg) { type = NavType.StringType }
    )
}