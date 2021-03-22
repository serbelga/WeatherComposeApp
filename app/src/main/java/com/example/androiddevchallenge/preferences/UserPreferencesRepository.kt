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
package com.example.androiddevchallenge.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.model.WindSpeedUnit
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.Companion.DATA_STORE_NAME
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.PreferencesKeys.CITY_SELECTED
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.PreferencesKeys.TEMPERATURE_UNIT
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.PreferencesKeys.USER_THEME
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.PreferencesKeys.WIND_SPEED_UNIT
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext val context: Context
) {

    fun isDarkThemeEnabled(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[USER_THEME] ?: false
    }

    suspend fun setDarkThemeEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_THEME] = enabled
        }
    }

    fun getTemperatureUnit(): Flow<TemperatureUnit> = context.dataStore.data.map { preferences ->
        val ordinal = preferences[TEMPERATURE_UNIT] ?: 0
        enumValues<TemperatureUnit>().getOrNull(ordinal) ?: TemperatureUnit.CELSIUS
    }

    suspend fun setTemperatureUnit(temperatureUnit: TemperatureUnit) {
        context.dataStore.edit { preferences ->
            preferences[TEMPERATURE_UNIT] = temperatureUnit.ordinal
        }
    }

    fun getWindSpeedUnit(): Flow<WindSpeedUnit> = context.dataStore.data.map { preferences ->
        val ordinal = preferences[WIND_SPEED_UNIT] ?: 0
        enumValues<WindSpeedUnit>().getOrNull(ordinal) ?: WindSpeedUnit.KILOMETERS_PER_HOUR
    }

    suspend fun setWindSpeedUnit(windSpeedUnit: WindSpeedUnit) {
        context.dataStore.edit { preferences ->
            preferences[WIND_SPEED_UNIT] = windSpeedUnit.ordinal
        }
    }

    fun getCitySelected(): Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[CITY_SELECTED] ?: 1
    }

    suspend fun setCitySelected(cityId: Int) {
        context.dataStore.edit { preferences ->
            preferences[CITY_SELECTED] = cityId
        }
    }

    private object PreferencesKeys {
        val USER_THEME = booleanPreferencesKey(USER_THEME_KEY)
        val TEMPERATURE_UNIT = intPreferencesKey(TEMPERATURE_UNIT_KEY)
        val WIND_SPEED_UNIT = intPreferencesKey(WIND_SPEED_UNIT_KEY)
        val CITY_SELECTED = intPreferencesKey(CITY_SELECTED_UNIT_KEY)
    }

    companion object {
        const val DATA_STORE_NAME = "preferences"
        private const val USER_THEME_KEY = "user_theme"
        private const val TEMPERATURE_UNIT_KEY = "temperature_unit"
        private const val WIND_SPEED_UNIT_KEY = "wind_speed_unit"
        private const val CITY_SELECTED_UNIT_KEY = "city_selected"
    }
}
