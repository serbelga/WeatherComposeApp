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
    temperature = Temperature(20f, TemperatureUnit.CELSIUS),
    humidity = 20,
    windSpeed = 60,
    weather = Weather.WINDY
)
