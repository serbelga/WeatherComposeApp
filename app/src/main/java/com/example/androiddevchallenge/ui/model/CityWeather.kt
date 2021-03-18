package com.example.androiddevchallenge.ui.model

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

data class City(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

data class Temperature(
    val value: Double,
    val unit: TemperatureUnit
) {
    override fun toString(): String {
        return "$value${unit.symbol}"
    }
}

enum class TemperatureUnit(val symbol: String) {
    CELSIUS("ºC")
}

enum class Weather(val value: String) {
    SUNNY("Sunny"),
    CLOUDY("Cloudy"),
    RAINY("Rainy"),
    SNOWY("Snowy"),
    STORMY("Stormy"),
    WINDY("Windy"),
    FOGGY("Foggy")
}

val valencia = City(
    id = 1,
    name = "Valencia",
    latitude = 39.46,
    longitude = -0.375
)

val valenciaWeather = CityWeather(
    city = valencia,
    temperature = Temperature(24.0, TemperatureUnit.CELSIUS),
    humidity = 20,
    windSpeed = 60,
    weather = Weather.SUNNY
)
