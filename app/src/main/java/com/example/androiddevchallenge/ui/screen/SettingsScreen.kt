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
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.City
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.model.WindSpeedUnit
import com.example.androiddevchallenge.ui.components.HorizontalDivider
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.viewmodel.WeatherViewModel
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SettingsScreen(
    weatherViewModel: WeatherViewModel,
    navigateUp: () -> Unit
) {
    val userPreferenceTemperatureUnit =
        weatherViewModel.userPreferenceTemperatureUnit.collectAsState(initial = null)
    val userPreferenceWindSpeedUnit = weatherViewModel.userPreferenceWindSpeedUnit.collectAsState(
        initial = null
    )
    val cityWeather = weatherViewModel.cityWeather.observeAsState()
    val cities = weatherViewModel.cities
    Scaffold(
        topBar = {
            SettingsTopAppBar(navigationIconClick = navigateUp)
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CitySelectedSettings(cities, cityWeather.value?.city?.id) {
                weatherViewModel.setCitySelected(it)
            }
            HorizontalDivider()
            TemperatureUnitSettings(userPreferenceTemperatureUnit.value) {
                weatherViewModel.setTemperatureUnit(it)
            }
            HorizontalDivider()
            WindSpeedUnitSettings(userPreferenceWindSpeedUnit.value) {
                weatherViewModel.setWindSpeedUnit(it)
            }
        }
    }
}

@Composable
fun CitySelectedSettings(
    cities: List<City>,
    selected: Int?,
    onCitySelected: (City) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        SettingsPreferenceTitle(stringResource(R.string.city))
        cities.forEach {
            CityItem(city = it, selected) { city ->
                onCitySelected(city)
            }
        }
        TextButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Icon(Icons.Rounded.Add, "Add city")
            Text("Add city", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun CityItem(
    city: City,
    selected: Int?,
    onCitySelected: (City) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clickable {
                onCitySelected(city)
            }
    ) {
        Icon(Icons.Outlined.Place, "City", modifier = Modifier.padding(start = 16.dp))
        Text(
            city.name,
            Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )
        if (city.id == selected) {
            Icon(Icons.Rounded.Check, "Selected", modifier = Modifier.padding(end = 16.dp))
        }
    }
}

@Composable
fun TemperatureUnitSettings(
    userPreferenceTemperatureUnit: TemperatureUnit?,
    onTemperatureUnitSelected: (TemperatureUnit) -> Unit
) {
    val temperatureUnits = enumValues<TemperatureUnit>()
    Column {
        SettingsPreferenceTitle(stringResource(R.string.temperature_unit))
        temperatureUnits.forEach {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (it == userPreferenceTemperatureUnit),
                        onClick = {
                            onTemperatureUnitSelected(it)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (it == userPreferenceTemperatureUnit),
                    onClick = null
                )
                Text(
                    text = "${it.description} (${it.symbol})",
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun WindSpeedUnitSettings(
    userPreferenceWindSpeedUnit: WindSpeedUnit?,
    onWindSpeedUnit: (WindSpeedUnit) -> Unit
) {
    val windSpeedUnits = enumValues<WindSpeedUnit>()
    Column {
        SettingsPreferenceTitle(stringResource(R.string.wind_speed))
        windSpeedUnits.forEach {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (it == userPreferenceWindSpeedUnit),
                        onClick = { onWindSpeedUnit(it) }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (it == userPreferenceWindSpeedUnit),
                    onClick = null
                )
                Text(
                    text = "${it.description} (${it.symbol})",
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SettingsPreferenceTitle(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.caption,
        modifier = Modifier
            .paddingFromBaseline(top = 24.dp)
            .padding(start = 16.dp, bottom = 16.dp)
    )
}

@Composable
fun SettingsTopAppBar(navigationIconClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigationIconClick) {
                Icon(Icons.Rounded.KeyboardBackspace, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        title = {
            Text(text = stringResource(id = R.string.settings))
        },
        elevation = 0.dp
    )
}

@Composable
@Preview(widthDp = 360)
fun SettingsTopAppBarPreview() {
    WeatherTheme {
        SettingsTopAppBar {
        }
    }
}

@Composable
@Preview(widthDp = 360)
fun SettingsTopAppBarDarkPreview() {
    WeatherTheme(true) {
        SettingsTopAppBar {
        }
    }
}
