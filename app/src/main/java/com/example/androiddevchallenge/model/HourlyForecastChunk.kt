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
package com.example.androiddevchallenge.model

data class HourlyForecast(
    val minValue: Float,
    val maxValue: Float,
    val chunks: List<HourlyForecastChunk>
)

data class HourlyForecastChunk(
    val temperature: Temperature,
    val time: String,
    val weather: Weather
)

val hourlyForecastChunks = listOf(
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

val valenciaHourlyForecast = HourlyForecast(
    19f,
    25f,
    hourlyForecastChunks
)
