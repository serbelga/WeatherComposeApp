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

data class Temperature(
    val value: Float,
    val unit: TemperatureUnit = TemperatureUnit.CELSIUS
) {
    override fun toString(): String = "${value.toInt()}${unit.symbol}"
}

data class WindSpeed(
    val value: Float,
    val unit: WindSpeedUnit = WindSpeedUnit.KILOMETERS_PER_HOUR
) {
    override fun toString(): String = "${value.toInt()}${unit.symbol}"
}

// TODO: 21/03/2021 Move to strings res
enum class TemperatureUnit(val description: String, val symbol: String) {
    CELSIUS("Celsius", "ºC"),
    FAHRENHEIT("Fahrenheit", "ºF")
}

enum class WindSpeedUnit(val description: String, val symbol: String) {
    KILOMETERS_PER_HOUR("Kilometers per hour", "km/h"),
    MILES_PER_HOUR("Miles per hour", "mph")
}