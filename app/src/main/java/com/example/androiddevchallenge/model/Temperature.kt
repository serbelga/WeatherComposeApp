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

    fun getStringAs(unit: TemperatureUnit): String =
        when (this.unit) {
            TemperatureUnit.CELSIUS -> stringFormat(celsiusTo(unit), unit)
            TemperatureUnit.FAHRENHEIT -> stringFormat(fahrenheitTo(unit), unit)
        }

    fun getFloatAs(unit: TemperatureUnit): Float =
        when (this.unit) {
            TemperatureUnit.CELSIUS -> celsiusTo(unit)
            TemperatureUnit.FAHRENHEIT -> fahrenheitTo(unit)
        }

    private fun celsiusTo(unit: TemperatureUnit): Float =
        when (unit) {
            TemperatureUnit.FAHRENHEIT -> (value * 1.8f) + 32
            else -> value
        }

    private fun fahrenheitTo(unit: TemperatureUnit): Float =
        when (unit) {
            TemperatureUnit.FAHRENHEIT -> (value - 32) / 1.8f
            else -> value
        }

    override fun toString(): String = stringFormat(value, unit)

    private fun stringFormat(value: Float, unit: TemperatureUnit) = "${value.toInt()}${unit.symbol}"
}

// TODO: 21/03/2021 Move to strings res
enum class TemperatureUnit(val description: String, val symbol: String) {
    CELSIUS("Celsius", "ºC"),
    FAHRENHEIT("Fahrenheit", "ºF")
}
