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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.androiddevchallenge.ui.screen.WeatherScreen
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.viewmodel.WeatherViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.Destinations.Home
import com.example.androiddevchallenge.ui.Destinations.Settings
import com.example.androiddevchallenge.ui.screen.SettingsScreen

@Composable
fun WeatherApp(weatherViewModel: WeatherViewModel) {
    val cityWeatherState = weatherViewModel.cityWeather.observeAsState()
    // val darkThemeEnabled = weatherViewModel.isDarkTheme.collectAsState(initial = false)
    val dark = cityWeatherState.value?.let {
        it.timestamp < it.sunriseTimestamp || it.timestamp > it.sunsetTimestamp
    } ?: false
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    WeatherTheme(dark) {
        ProvideWindowInsets {
            NavHost(navController = navController, startDestination = Home) {
                composable(Home) {
                    WeatherScreen(
                        weatherViewModel,
                        actions.navToSettings
                    )
                }
                composable(Settings) {
                    SettingsScreen(
                        weatherViewModel,
                        actions.navigateUp
                    )
                }
            }
        }
    }
}
