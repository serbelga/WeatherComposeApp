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

import com.example.androiddevchallenge.util.DateUtil.formatMillis

data class CityWeather(
    val city: City,
    val timestamp: Long,
    val day: String,
    val temperature: Temperature,
    val humidity: Int? = null,
    val windSpeed: WindSpeed,
    val weather: Weather,
    val sunriseTimestamp: Long,
    val sunsetTimestamp: Long
) {
    fun getHumidity(): String = "$humidity%"

    fun formatDayAndTimestamp(): String = "$day ${formatMillis(timestamp)}"
}
