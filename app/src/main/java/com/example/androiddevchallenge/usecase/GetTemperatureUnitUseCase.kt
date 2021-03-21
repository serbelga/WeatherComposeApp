package com.example.androiddevchallenge.usecase

import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetTemperatureUnitUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    operator fun invoke(): Flow<TemperatureUnit> = userPreferencesRepository.getTemperatureUnit()
}
