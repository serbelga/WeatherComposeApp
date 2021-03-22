/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.example.androiddevchallenge.model.WindSpeed
import com.example.androiddevchallenge.model.WindSpeedUnit

private val valencia = City(
    id = 1,
    name = "Valencia",
    latitude = 39.46,
    longitude = -0.375
)

private val tokyo = City(
    id = 2,
    name = "Tokyo",
    latitude = 0.0,
    longitude = 0.0
)

val cities = listOf(
    valencia,
    tokyo
)

val valenciaWeather = CityWeather(
    city = valencia,
    timestamp = 32400000,
    day = "Wednesday",
    temperature = Temperature(20f, TemperatureUnit.CELSIUS),
    humidity = 20,
    windSpeed = WindSpeed(60f, WindSpeedUnit.KILOMETERS_PER_HOUR),
    weather = Weather.PARTLY_CLOUDY,
    sunriseTimestamp = 25200000,
    sunsetTimestamp = 68640000
)

val tokyoWeather = CityWeather(
    city = tokyo,
    timestamp = 75600000,
    day = "Wednesday",
    temperature = Temperature(15f, TemperatureUnit.CELSIUS),
    humidity = 60,
    windSpeed = WindSpeed(3f, WindSpeedUnit.KILOMETERS_PER_HOUR),
    weather = Weather.CLEAR,
    sunriseTimestamp = 25200000,
    sunsetTimestamp = 68640000
)

val citiesWeather = listOf(
    valenciaWeather,
    tokyoWeather
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
        Weather.PARTLY_CLOUDY
    ),
    DailyForecast(
        "Saturday",
        Temperature(25f, TemperatureUnit.CELSIUS),
        Temperature(20f, TemperatureUnit.CELSIUS),
        Weather.PARTLY_CLOUDY
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
        Weather.PARTLY_CLOUDY
    ),
    DailyForecast(
        "Tuesday",
        Temperature(16f, TemperatureUnit.CELSIUS),
        Temperature(10f, TemperatureUnit.CELSIUS),
        Weather.HEAVY_RAIN
    )
)

private val tokyoDailyForecastList = listOf(
    DailyForecast(
        "Tomorrow",
        Temperature(15f, TemperatureUnit.CELSIUS),
        Temperature(7f, TemperatureUnit.CELSIUS),
        Weather.CLEAR
    ),
    DailyForecast(
        "Friday",
        Temperature(16f, TemperatureUnit.CELSIUS),
        Temperature(8f, TemperatureUnit.CELSIUS),
        Weather.WINDY
    ),
    DailyForecast(
        "Saturday",
        Temperature(19f, TemperatureUnit.CELSIUS),
        Temperature(9f, TemperatureUnit.CELSIUS),
        Weather.CLOUDY
    ),
    DailyForecast(
        "Sunday",
        Temperature(17f, TemperatureUnit.CELSIUS),
        Temperature(8f, TemperatureUnit.CELSIUS),
        Weather.LIGHT_RAIN
    ),
    DailyForecast(
        "Monday",
        Temperature(20f, TemperatureUnit.CELSIUS),
        Temperature(12f, TemperatureUnit.CELSIUS),
        Weather.MODERATE_RAIN
    ),
    DailyForecast(
        "Tuesday",
        Temperature(18f, TemperatureUnit.CELSIUS),
        Temperature(9f, TemperatureUnit.CELSIUS),
        Weather.HEAVY_RAIN
    )
)

private val valenciaDailyForecast = CityDailyForecast(
    cityId = 1,
    dailyForecastList = valenciaDailyForecastList
)

private val tokyoDailyForecast = CityDailyForecast(
    cityId = 2,
    dailyForecastList = tokyoDailyForecastList
)

val citiesDailyForecast = listOf(
    valenciaDailyForecast,
    tokyoDailyForecast
)

private val valenciaHourlyForecastChunks = listOf(
    HourlyForecastChunk(Temperature(20f, TemperatureUnit.CELSIUS), 46800000, Weather.CLEAR),
    HourlyForecastChunk(Temperature(21f, TemperatureUnit.CELSIUS), 50400000, Weather.CLEAR),
    HourlyForecastChunk(Temperature(23f, TemperatureUnit.CELSIUS), 54000000, Weather.CLEAR),
    HourlyForecastChunk(Temperature(24f, TemperatureUnit.CELSIUS), 57600000, Weather.CLEAR),
    HourlyForecastChunk(Temperature(23f, TemperatureUnit.CELSIUS), 61200000, Weather.PARTLY_CLOUDY),
    HourlyForecastChunk(Temperature(23f, TemperatureUnit.CELSIUS), 64800000, Weather.CLOUDY),
    HourlyForecastChunk(Temperature(22f, TemperatureUnit.CELSIUS), 68400000, Weather.CLOUDY),
    HourlyForecastChunk(Temperature(21f, TemperatureUnit.CELSIUS), 72000000, Weather.CLOUDY),
    HourlyForecastChunk(Temperature(19f, TemperatureUnit.CELSIUS), 75600000, Weather.LIGHT_RAIN)
)

private val tokyoHourlyForecastChunks = listOf(
    HourlyForecastChunk(Temperature(15f, TemperatureUnit.CELSIUS), 75600000, Weather.CLEAR),
    HourlyForecastChunk(Temperature(14f, TemperatureUnit.CELSIUS), 79200000, Weather.CLOUDY),
    HourlyForecastChunk(Temperature(12f, TemperatureUnit.CELSIUS), 82800000, Weather.LIGHT_RAIN),
    HourlyForecastChunk(Temperature(10f, TemperatureUnit.CELSIUS), 0, Weather.MODERATE_RAIN),
    HourlyForecastChunk(Temperature(8f, TemperatureUnit.CELSIUS), 3600000, Weather.HEAVY_RAIN),
    HourlyForecastChunk(Temperature(7f, TemperatureUnit.CELSIUS), 7200000, Weather.CLOUDY),
    HourlyForecastChunk(Temperature(7f, TemperatureUnit.CELSIUS), 10800000, Weather.MODERATE_RAIN),
    HourlyForecastChunk(Temperature(6f, TemperatureUnit.CELSIUS), 14400000, Weather.CLOUDY),
    HourlyForecastChunk(Temperature(6f, TemperatureUnit.CELSIUS), 18000000, Weather.CLOUDY)
)

private val valenciaHourlyForecast = CityHourlyForecast(
    1,
    Temperature(19f, TemperatureUnit.CELSIUS),
    Temperature(25f, TemperatureUnit.CELSIUS),
    valenciaHourlyForecastChunks
)

private val tokyoHourlyForecast = CityHourlyForecast(
    2,
    Temperature(6f, TemperatureUnit.CELSIUS),
    Temperature(15f, TemperatureUnit.CELSIUS),
    tokyoHourlyForecastChunks
)

val citiesHourlyForecast = listOf(
    valenciaHourlyForecast,
    tokyoHourlyForecast
)
