package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.datasource.citiesDailyForecast
import com.example.androiddevchallenge.datasource.citiesHourlyForecast
import com.example.androiddevchallenge.datasource.citiesWeather
import com.example.androiddevchallenge.model.CityDailyForecast
import com.example.androiddevchallenge.model.CityHourlyForecast
import com.example.androiddevchallenge.model.CityWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor() {

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