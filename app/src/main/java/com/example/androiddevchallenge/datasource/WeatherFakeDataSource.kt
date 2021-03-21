package com.example.androiddevchallenge.datasource

import com.example.androiddevchallenge.model.City
import com.example.androiddevchallenge.model.CityDailyForecast
import com.example.androiddevchallenge.model.CityHourlyForecast
import com.example.androiddevchallenge.model.CityWeather
import com.example.androiddevchallenge.model.DailyForecast
import com.example.androiddevchallenge.model.HourlyForecastChunk
import com.example.androiddevchallenge.model.Temperature
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.model.Weather

val valencia = City(
    id = 1,
    name = "Valencia",
    latitude = 39.46,
    longitude = -0.375
)

val japan = City(
    id = 2,
    name = "Japan",
    latitude = 0.0,
    longitude = 0.0
)

val valenciaWeather = CityWeather(
    city = valencia,
    timestamp = 0,
    day = "Wednesday",
    temperature = Temperature(20f, TemperatureUnit.CELSIUS),
    humidity = 20,
    windSpeed = 60,
    weather = Weather.WINDY,
    sunriseTimestamp = 25200000,
    sunsetTimestamp = 68640000
)

val japanWeather = CityWeather(
    city = japan,
    timestamp = 46800000,
    day = "Wednesday",
    temperature = Temperature(20f, TemperatureUnit.CELSIUS),
    humidity = 20,
    windSpeed = 60,
    weather = Weather.WINDY,
    sunriseTimestamp = 25200000,
    sunsetTimestamp = 68640000
)

val citiesWeather = listOf(
    valenciaWeather,
    japanWeather
)

private val valenciaDailyForecastList = listOf(
    DailyForecast(
        "Tomorrow",
        Temperature(27f, TemperatureUnit.CELSIUS),
        Temperature(24f, TemperatureUnit.CELSIUS),
        Weather.CLEAR
    ),
    DailyForecast(
        "Friday",
        Temperature(26f, TemperatureUnit.CELSIUS),
        Temperature(21f, TemperatureUnit.CELSIUS),
        Weather.CLEAR
    ),
    DailyForecast(
        "Saturday",
        Temperature(25f, TemperatureUnit.CELSIUS),
        Temperature(20f, TemperatureUnit.CELSIUS),
        Weather.CLEAR
    ),
    DailyForecast(
        "Sunday",
        Temperature(26f, TemperatureUnit.CELSIUS),
        Temperature(21f, TemperatureUnit.CELSIUS),
        Weather.CLEAR
    ),
    DailyForecast(
        "Monday",
        Temperature(18f, TemperatureUnit.CELSIUS),
        Temperature(12f, TemperatureUnit.CELSIUS),
        Weather.MODERATE_RAIN
    ),
    DailyForecast(
        "Tuesday",
        Temperature(16f, TemperatureUnit.CELSIUS),
        Temperature(10f, TemperatureUnit.CELSIUS),
        Weather.HEAVY_RAIN
    )
)

private val valenciaDailyForecast = CityDailyForecast(
    cityId = 1,
    dailyForecastList = valenciaDailyForecastList
)

val citiesDailyForecast = listOf(
    valenciaDailyForecast
)

private val valenciaHourlyForecastChunks = listOf(
    HourlyForecastChunk(Temperature(20f, TemperatureUnit.CELSIUS), "13:00h", Weather.CLEAR),
    HourlyForecastChunk(Temperature(21f, TemperatureUnit.CELSIUS), "14:00h", Weather.CLEAR),
    HourlyForecastChunk(Temperature(23f, TemperatureUnit.CELSIUS), "15:00h", Weather.LIGHT_RAIN),
    HourlyForecastChunk(Temperature(24f, TemperatureUnit.CELSIUS), "16:00h", Weather.MODERATE_RAIN),
    HourlyForecastChunk(Temperature(23f, TemperatureUnit.CELSIUS), "17:00h", Weather.HEAVY_RAIN),
    HourlyForecastChunk(Temperature(23f, TemperatureUnit.CELSIUS), "18:00h", Weather.CLOUDY),
    HourlyForecastChunk(Temperature(22f, TemperatureUnit.CELSIUS), "19:00h", Weather.CLOUDY),
    HourlyForecastChunk(Temperature(21f, TemperatureUnit.CELSIUS), "20:00h", Weather.CLOUDY),
    HourlyForecastChunk(Temperature(19f, TemperatureUnit.CELSIUS), "21:00h", Weather.CLOUDY)
)

private val valenciaHourlyForecast = CityHourlyForecast(
    1,
    19f,
    25f,
    valenciaHourlyForecastChunks
)

val citiesHourlyForecast = listOf(
    valenciaHourlyForecast
)
