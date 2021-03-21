package com.example.androiddevchallenge.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.androiddevchallenge.preferences.UserPreferencesRepository.Companion.DATA_STORE_NAME
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

    private object PreferencesKeys {
        val USER_THEME = booleanPreferencesKey(USER_THEME_KEY)
    }

    companion object {
        const val DATA_STORE_NAME = "preferences"
        private const val USER_THEME_KEY = "user_theme"
    }
}
