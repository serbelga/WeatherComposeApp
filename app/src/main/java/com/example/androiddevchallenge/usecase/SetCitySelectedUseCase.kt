package com.example.androiddevchallenge.usecase

import com.example.androiddevchallenge.preferences.UserPreferencesRepository

class SetCitySelectedUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    suspend operator fun invoke(cityId: Int) = userPreferencesRepository.setCitySelected(cityId)
}
