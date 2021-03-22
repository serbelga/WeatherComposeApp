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
package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.preferences.UserPreferencesRepository
import com.example.androiddevchallenge.repository.WeatherRepository
import com.example.androiddevchallenge.usecase.GetCitiesUseCase
import com.example.androiddevchallenge.usecase.GetCityDailyForecastUseCase
import com.example.androiddevchallenge.usecase.GetCityHourlyForecastUseCase
import com.example.androiddevchallenge.usecase.GetCityWeatherUseCase
import com.example.androiddevchallenge.usecase.GetTemperatureUnitUseCase
import com.example.androiddevchallenge.usecase.GetWindSpeedUnitUseCase
import com.example.androiddevchallenge.usecase.SetCitySelectedUseCase
import com.example.androiddevchallenge.usecase.SetTemperatureUnitUseCase
import com.example.androiddevchallenge.usecase.SetWindSpeedUnitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetCityWeatherUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        weatherRepository: WeatherRepository
    ) = GetCityWeatherUseCase(userPreferencesRepository, weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideGetCityHourlyForecastUseCase(
        weatherRepository: WeatherRepository,
        userPreferencesRepository: UserPreferencesRepository
    ) = GetCityHourlyForecastUseCase(userPreferencesRepository, weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideGetCityDailyForecastUseCase(
        weatherRepository: WeatherRepository,
        userPreferencesRepository: UserPreferencesRepository
    ) = GetCityDailyForecastUseCase(userPreferencesRepository, weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideGetTemperatureUnitUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = GetTemperatureUnitUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideSetTemperatureUnitUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetTemperatureUnitUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideGetWindSpeedUnitUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = GetWindSpeedUnitUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideSetWindSpeedUnitUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetWindSpeedUnitUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideGetCitiesUseCase(
        weatherRepository: WeatherRepository
    ) = GetCitiesUseCase(weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideSetCitySelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetCitySelectedUseCase(userPreferencesRepository)
}
