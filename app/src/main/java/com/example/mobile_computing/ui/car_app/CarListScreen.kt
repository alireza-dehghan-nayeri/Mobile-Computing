package com.example.mobile_computing.ui.car_app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile_computing.data.car_app.Car

@Composable
fun CarListScreen(onCarClick: (Car) -> Unit, cars: List<Car>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(cars) { car ->
            CarCard(onCarClick, car)

        }
    }
}

@Composable
fun CarCard(onCarClick: (Car) -> Unit, car: Car) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(car.brand.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)


                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "${car.brand.name} ${car.model}",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = car.year.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = painterResource(car.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCarClick(car)
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = car.detail,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        onCarClick(car)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Details")
                    }
                }
            }


        }
    }
}