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
package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.datasource.cities
import com.example.androiddevchallenge.datasource.citiesDailyForecast
import com.example.androiddevchallenge.datasource.citiesHourlyForecast
import com.example.androiddevchallenge.datasource.citiesWeather
import com.example.androiddevchallenge.model.City
import com.example.androiddevchallenge.model.CityDailyForecast
import com.example.androiddevchallenge.model.CityHourlyForecast
import com.example.androiddevchallenge.model.CityWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor() {

    fun getCities(): List<City> = cities

    fun getCityWeather(id: Int): Flow<CityWeather?> = flow {
        emit(citiesWeather.firstOrNull { it.city.id == id })
    }

    fun getCityDailyForecast(id: Int): Flow<CityDailyForecast?> = flow {
        emit(citiesDailyForecast.firstOrNull { it.cityId == id })
    }

    fun getCityHourlyForecast(id: Int): Flow<CityHourlyForecast?> = flow {
        emit(citiesHourlyForecast.firstOrNull { it.cityId == id })
    }
}
