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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.DailyForecastCard
import com.example.androiddevchallenge.ui.components.HorizontalDivider
import com.example.androiddevchallenge.ui.components.HourlyForecastCard
import com.example.androiddevchallenge.ui.components.SunriseSunsetCard
import com.example.androiddevchallenge.viewmodel.WeatherViewModel
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel,
    navToSettings: () -> Unit
) {
    val cityWeather = weatherViewModel.cityWeather.observeAsState()
    val cityHourlyForecast = weatherViewModel.cityHourlyForecast.observeAsState()
    val cityDailyForecast = weatherViewModel.cityDailyForecast.observeAsState()
    val scrollState = rememberScrollState(0)
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.background),
                title = {
                    Column(
                        modifier = Modifier.padding(start = 24.dp, top = 8.dp)
                    ) {
                        // TODO 750 as const
                        if (scrollState.value >= 750) {
                            Text(
                                text = cityWeather.value?.city?.name ?: "",
                                style = MaterialTheme.typography.h6
                            )
                        } else {
                            Text(
                                text = cityWeather.value?.city?.name ?: "",
                                style = MaterialTheme.typography.h5
                            )
                        }
                        AnimatedVisibility(visible = scrollState.value >= 750) {
                            Text(
                                text = cityWeather.value?.temperature?.toString() ?: ""
                            )
                        }
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            if (scrollState.value >= 750) {
                                Text(
                                    text = "Wednesday, 13:00h",
                                    style = MaterialTheme.typography.caption,
                                )
                            } else {
                                Text(
                                    text = "Wednesday, 13:00h",
                                    style = MaterialTheme.typography.body1,
                                )
                            }
                        }
                    }
                },
                actions = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        IconButton(
                            onClick = navToSettings,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Tune,
                                contentDescription = "Settings"
                            )
                        }
                    }
                },
                modifier = Modifier.height(96.dp)
            )
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            cityWeather.value?.let { cityWeather ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 64.dp, top = 48.dp)
                ) {
                    val drawableResId =
                        if (MaterialTheme.colors.isLight)
                            cityWeather.weather.drawableResId
                        else
                            cityWeather.weather.nightDrawableResId
                    Image(
                        painterResource(id = drawableResId),
                        contentDescription = "",
                        modifier = Modifier
                            .height(160.dp)
                            .width(200.dp)
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            stringResource(id = cityWeather.weather.stringResId),
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    Text(
                        text = cityWeather.temperature.toString(),
                        style = MaterialTheme.typography.h3
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Icon(
                                Icons.Rounded.Air,
                                "Wind speed"
                            )
                        }
                        Text(
                            text = cityWeather.getWindSpeed()
                        )
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Icon(
                                painterResource(id = R.drawable.ic_water_outline_24),
                                "Humidity"
                            )
                        }
                        Text(text = cityWeather.getHumidity())
                    }
                }
            }
            cityHourlyForecast.value?.let { HourlyForecastCard(it) }
            cityDailyForecast.value?.let { DailyForecastCard(it) }
            cityWeather.value?.let { SunriseSunsetCard(it.sunriseTimestamp, it.sunsetTimestamp) }
        }
        if (scrollState.value >= 175) {
            HorizontalDivider()
        }
    }
}

// TODO Remove comment
/*
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    WeatherTheme {
        WeatherScreen(valenciaWeather)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    WeatherTheme(darkTheme = true) {
        WeatherScreen(valenciaWeather)
    }
}*/
