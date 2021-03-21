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
package com.example.androiddevchallenge.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androiddevchallenge.model.CityDailyForecast
import com.example.androiddevchallenge.model.CityHourlyForecast
import com.example.androiddevchallenge.model.CityWeather
import com.example.androiddevchallenge.usecase.GetCityDailyForecastUseCase
import com.example.androiddevchallenge.usecase.GetCityHourlyForecastUseCase
import com.example.androiddevchallenge.usecase.GetCityWeatherUseCase
import com.example.androiddevchallenge.usecase.IsDarkThemeEnabledUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    getCityWeatherUseCase: GetCityWeatherUseCase,
    getCityHourlyForecastUseCase: GetCityHourlyForecastUseCase,
    getCityDailyForecastUseCase: GetCityDailyForecastUseCase,
    isDarkThemeEnabledUseCase: IsDarkThemeEnabledUseCase
) : ViewModel() {

    val cityWeather: LiveData<CityWeather?> = getCityWeatherUseCase(1).asLiveData()

    val cityDailyForecast: LiveData<CityDailyForecast?> = getCityDailyForecastUseCase(1).asLiveData()

    val cityHourlyForecast: LiveData<CityHourlyForecast?> =
        getCityHourlyForecastUseCase.invoke(1).asLiveData()

    val isDarkTheme: Flow<Boolean> = isDarkThemeEnabledUseCase()
}
