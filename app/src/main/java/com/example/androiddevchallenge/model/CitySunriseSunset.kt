package com.example.androiddevchallenge.model

data class CitySunriseSunset(
    val cityId: Int,
    val sunriseTimestamp: Long,
    val sunsetTimestamp: Long
)

val valenciaSunriseSunset = CitySunriseSunset(
    cityId = 1,
    sunriseTimestamp = 25200000,
    sunsetTimestamp = 68640000
)
