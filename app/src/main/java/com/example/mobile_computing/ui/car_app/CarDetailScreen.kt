package com.example.mobile_computing.ui.car_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile_computing.data.car_app.Car

@Composable
fun CarDetailScreen(onBackButtonClick: () -> Unit, car: Car, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Image(
            painter = painterResource(car.image),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = car.detail
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            onBackButtonClick()
        }) {
            Text(text = "Back")
        }

    }
}