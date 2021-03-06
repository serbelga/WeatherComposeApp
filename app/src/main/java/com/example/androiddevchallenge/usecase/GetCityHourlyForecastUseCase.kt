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
package com.example.androiddevchallenge.usecase

import com.example.androiddevchallenge.model.CityHourlyForecast
import com.example.androiddevchallenge.preferences.UserPreferencesRepository
import com.example.androiddevchallenge.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class GetCityHourlyForecastUseCase(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val weatherRepository: WeatherRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<CityHourlyForecast?> =
        userPreferencesRepository.getCitySelected().flatMapLatest {
            weatherRepository.getCityHourlyForecast(it)
        }
}
