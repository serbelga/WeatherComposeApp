package com.example.androiddevchallenge.usecase

import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.preferences.UserPreferencesRepository

class SetTemperatureUnitUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    suspend operator fun invoke(temperatureUnit: TemperatureUnit) = userPreferencesRepository.setTemperatureUnit(temperatureUnit)
}