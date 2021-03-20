package com.example.androiddevchallenge.model

data class CityWeather(
    val city: City,
    val temperature: Temperature,
    val humidity: Int? = null,
    val windSpeed: Int? = null,
    val weather: Weather
) {
    fun getHumidity(): String = "$humidity%"

    fun getWindSpeed(): String = "$windSpeed km/h"
}

val valenciaWeather = CityWeather(
    city = valencia,
    temperature = Temperature(24.0, TemperatureUnit.CELSIUS),
    humidity = 20,
    windSpeed = 60,
    weather = Weather.SUNNY
)
