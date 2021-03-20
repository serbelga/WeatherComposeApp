package com.example.androiddevchallenge.model

data class DailyForecast(
    val day: String,
    val temperatureMax: Temperature,
    val temperatureMin: Temperature,
    val weather: Weather
)

val dailyForecastList = listOf(
    DailyForecast(
        "Tomorrow",
        Temperature(27.0, TemperatureUnit.CELSIUS),
        Temperature(24.0, TemperatureUnit.CELSIUS),
        Weather.SUNNY
    ),
    DailyForecast(
        "Friday",
        Temperature(26.0, TemperatureUnit.CELSIUS),
        Temperature(21.0, TemperatureUnit.CELSIUS),
        Weather.SUNNY
    ),
    DailyForecast(
        "Saturday",
        Temperature(25.0, TemperatureUnit.CELSIUS),
        Temperature(20.0, TemperatureUnit.CELSIUS),
        Weather.SUNNY
    ),
    DailyForecast(
        "Sunday",
        Temperature(26.0, TemperatureUnit.CELSIUS),
        Temperature(21.0, TemperatureUnit.CELSIUS),
        Weather.SUNNY
    ),
    DailyForecast(
        "Monday",
        Temperature(18.0, TemperatureUnit.CELSIUS),
        Temperature(12.0, TemperatureUnit.CELSIUS),
        Weather.MODERATE_RAIN
    ),
    DailyForecast(
        "Tuesday",
        Temperature(16.0, TemperatureUnit.CELSIUS),
        Temperature(10.0, TemperatureUnit.CELSIUS),
        Weather.HEAVY_RAIN
    )
)
