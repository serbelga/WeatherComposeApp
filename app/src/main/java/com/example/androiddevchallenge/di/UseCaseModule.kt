package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.preferences.UserPreferencesRepository
import com.example.androiddevchallenge.repository.WeatherRepository
import com.example.androiddevchallenge.usecase.GetCityDailyForecastUseCase
import com.example.androiddevchallenge.usecase.GetCityHourlyForecastUseCase
import com.example.androiddevchallenge.usecase.GetCityWeatherUseCase
import com.example.androiddevchallenge.usecase.IsDarkThemeEnabledUseCase
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
        weatherRepository: WeatherRepository
    ) = GetCityHourlyForecastUseCase(weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideGetCityDailyForecastUseCase(
        weatherRepository: WeatherRepository
    ) = GetCityDailyForecastUseCase(weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideIsDarkThemeEnabledUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = IsDarkThemeEnabledUseCase(userPreferencesRepository)
}
