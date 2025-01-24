package com.example.mobile_computing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile_computing.ui.theme.Mobile_ComputingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile_ComputingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JDMCars(
                        cars = SampleData.carSample,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Car(
    val brand: String,
    val brandImage: Int,
    val model: String,
    val year: Int,
    val detail: String,
    val image: Int
)

@Composable
fun CarCard(car: Car) {

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
                    painter = painterResource(car.brandImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)


                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "${car.brand} ${car.model}",
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
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = car.detail,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }


        }
    }
}


@Composable
fun JDMCars(cars: List<Car>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(cars) { car ->
            CarCard(car)

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CarCardPreview() {
    CarCard(SampleData.carSample.first())
}

@Preview(showBackground = true)
@Composable
private fun JDMCarsPreview() {
    Mobile_ComputingTheme {
        JDMCars(SampleData.carSample)
    }
}
