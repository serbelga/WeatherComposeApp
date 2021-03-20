package com.example.androiddevchallenge.model

data class Temperature(
    val value: Double,
    val unit: TemperatureUnit
) {
    override fun toString(): String {
        return "${value.toInt()}${unit.symbol}"
    }
}

enum class TemperatureUnit(val symbol: String) {
    CELSIUS("ºC"),
    FAHRENHEIT("ºF")
}