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

data class WindSpeed(
    val value: Float,
    val unit: WindSpeedUnit = WindSpeedUnit.KILOMETERS_PER_HOUR
) {

    fun getStringAs(unit: WindSpeedUnit): String =
        when (this.unit) {
            WindSpeedUnit.KILOMETERS_PER_HOUR -> stringFormat(kilometersPerHourTo(unit), unit)
            WindSpeedUnit.MILES_PER_HOUR -> stringFormat(milesPerHourTo(unit), unit)
        }

    fun getFloatAs(unit: WindSpeedUnit): Float =
        when (this.unit) {
            WindSpeedUnit.KILOMETERS_PER_HOUR -> kilometersPerHourTo(unit)
            WindSpeedUnit.MILES_PER_HOUR -> milesPerHourTo(unit)
        }

    private fun kilometersPerHourTo(unit: WindSpeedUnit): Float =
        when (unit) {
            WindSpeedUnit.MILES_PER_HOUR -> value * 0.621371f
            else -> value
        }

    private fun milesPerHourTo(unit: WindSpeedUnit): Float =
        when (unit) {
            WindSpeedUnit.KILOMETERS_PER_HOUR -> value / 0.621371f
            else -> value
        }

    override fun toString(): String = stringFormat(value, unit)

    private fun stringFormat(value: Float, unit: WindSpeedUnit) = "${value.toInt()}${unit.symbol}"
}

enum class WindSpeedUnit(val description: String, val symbol: String) {
    KILOMETERS_PER_HOUR("Kilometers per hour", "km/h"),
    MILES_PER_HOUR("Miles per hour", "mph")
}
