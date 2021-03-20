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

data class DailyForecast(
    val day: String,
    val temperatureMax: Temperature,
    val temperatureMin: Temperature,
    val weather: Weather
)

val dailyForecastList = listOf(
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
