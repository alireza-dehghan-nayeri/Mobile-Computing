package com.example.mobile_computing.data

import androidx.compose.runtime.Immutable

@Immutable
data class Car(
    val id: String,
    val brand: CarBrand,
    val model: String,
    val year: Int,
    val detail: String,
    val image: Int
)