package com.example.androiddevchallenge.model

data class City(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

val valencia = City(
    id = 1,
    name = "Valencia",
    latitude = 39.46,
    longitude = -0.375
)
