package com.example.androiddevchallenge.usecase

import com.example.androiddevchallenge.repository.WeatherRepository

class GetCitiesUseCase(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke() = weatherRepository.getCities()
}
