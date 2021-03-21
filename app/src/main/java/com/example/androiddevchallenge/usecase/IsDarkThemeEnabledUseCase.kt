package com.example.androiddevchallenge.usecase

import com.example.androiddevchallenge.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class IsDarkThemeEnabledUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    operator fun invoke(): Flow<Boolean> = userPreferencesRepository.isDarkThemeEnabled()
}