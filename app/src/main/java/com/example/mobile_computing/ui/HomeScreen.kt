package com.example.mobile_computing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile_computing.data.CarBrand

@Composable
fun HomeScreen(
    onCarBrandClick: (CarBrand) -> Unit,
    carBrands: List<CarBrand>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(carBrands) { carBrand ->
            CarBrandCard(onCarBrandClick, carBrand)
        }
    }
}

@Composable
fun CarBrandCard(
    onCarBrandClick: (CarBrand) -> Unit,
    carBrand: CarBrand,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onCarBrandClick(carBrand)
            }
    ) {


        Row {
            Image(
                painter = painterResource(carBrand.image),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = carBrand.name)
        }
    }
}