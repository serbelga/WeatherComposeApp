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
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyForecast
import com.example.androiddevchallenge.model.Temperature
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.model.Weather
import com.example.androiddevchallenge.model.dailyForecastList
import com.example.androiddevchallenge.ui.theme.WeatherTheme

@Composable
fun DailyForecastCard(dailyForecastList: List<DailyForecast>) {
    WeatherCard {
        Column {
            CardTitle(stringResource(id = R.string.daily_forecast))
            dailyForecastList.forEach {
                DailyForecastItem(dailyForecast = it)
            }
        }
    }
}

@Composable
fun DailyForecastItem(dailyForecast: DailyForecast) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            dailyForecast.day,
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = dailyForecast.temperatureMax.toString()
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = dailyForecast.temperatureMin.toString(),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Image(
            painterResource(id = dailyForecast.weather.drawableResId),
            contentDescription = "",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview(widthDp = 360)
@Composable
fun DailyForecastCardPreview() {
    WeatherTheme {
        DailyForecastCard(dailyForecastList)
    }
}

@Preview(widthDp = 360)
@Composable
fun DailyForecastItemPreview() {
    WeatherTheme {
        DailyForecastItem(
            DailyForecast(
                "Tomorrow",
                Temperature(27f, TemperatureUnit.CELSIUS),
                Temperature(21f, TemperatureUnit.CELSIUS),
                Weather.CLEAR
            )
        )
    }
}