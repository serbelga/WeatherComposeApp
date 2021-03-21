package com.example.androiddevchallenge.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.Companion.DATA_STORE_NAME
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.PreferencesKeys.TEMPERATURE_UNIT
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.PreferencesKeys.USER_THEME
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

    private object PreferencesKeys {
        val USER_THEME = booleanPreferencesKey(USER_THEME_KEY)
        val TEMPERATURE_UNIT = intPreferencesKey(TEMPERATURE_UNIT_KET)
    }

    companion object {
        const val DATA_STORE_NAME = "preferences"
        private const val USER_THEME_KEY = "user_theme"
        private const val TEMPERATURE_UNIT_KET = "temperature_unit"
    }
}
