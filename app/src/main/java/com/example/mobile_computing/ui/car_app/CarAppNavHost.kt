package com.example.mobile_computing.ui.car_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobile_computing.data.car_app.SampleData


@Composable
fun CarAppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {

        composable(route = Home.route) {
            HomeScreen(carBrands = SampleData.carBrandSample, onCarBrandClick = {
                navController.navigate("${CarList.route}/${it.id}")
            })
        }

        composable(route = CarList.routeWithArgs, arguments = CarList.arguments) { backStackEntry ->
            val brandId = backStackEntry.arguments?.getString(CarList.brandIdArg)
            CarListScreen(cars = SampleData.carSample.filter {
                it.brand.id == brandId
            }, onCarClick = {
                navController.navigate("${CarDetail.route}/${it.id}")
            })
        }

        composable(
            route = CarDetail.routeWithArgs,
            arguments = CarDetail.arguments
        ) { backStackEntry ->
            val carId = backStackEntry.arguments?.getString(CarDetail.carIdArg)
            CarDetailScreen(onBackButtonClick = {
                navController.navigateUp()
            }, car = SampleData.carSample.find {
                it.id == carId
            } ?: SampleData.carSample.first())
        }
    }

}
