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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.datasource.citiesHourlyForecast
import com.example.androiddevchallenge.model.CityHourlyForecast
import com.example.androiddevchallenge.model.HourlyForecastChunk
import com.example.androiddevchallenge.model.Temperature
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.model.Weather
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.ui.theme.chartLinearGradientLight
import com.example.androiddevchallenge.ui.theme.chartStrokeLight
import com.example.androiddevchallenge.ui.theme.selector
import com.example.androiddevchallenge.ui.theme.selectorShape
import com.example.androiddevchallenge.util.DateUtil.formatMillis

@Composable
fun HourlyForecastCard(
    hourlyForecast: CityHourlyForecast,
    isNight: Boolean,
    userPreferencesTemperatureUnit: TemperatureUnit
) {
    var selected by remember { mutableStateOf(0) }
    WeatherCard {
        Column {
            CardTitle(stringResource(id = R.string.hourly_forecast))
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(hourlyForecast.chunks) { index, item ->
                    HourlyForecastItem(
                        hourlyForecastChunk = item,
                        selected == index,
                        index,
                        isNight,
                        userPreferencesTemperatureUnit
                    ) {
                        selected = it
                    }
                }
            }
            HourlyForecastChart(
                minValue = hourlyForecast.minValue.getFloatAs(userPreferencesTemperatureUnit),
                maxValue = hourlyForecast.maxValue.getFloatAs(userPreferencesTemperatureUnit),
                selected = selected,
                hourlyForecast.chunks.map { it.temperature.getFloatAs(userPreferencesTemperatureUnit) }
            )
        }
    }
}

@Composable
fun HourlyForecastChart(
    minValue: Float,
    maxValue: Float,
    selected: Int,
    chunks: List<Float>
) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(120.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Height division -> given by maxValue - minValue.
        val chunkHeightDivision = if (maxValue > minValue) maxValue - minValue else 1f
        // Width of each chunk in chart.
        val chunkWidth = canvasWidth / (chunks.size + 1)

        // Stroke path.
        val path = Path()
        // Represent the area below the stroke path.
        val area = Path()

        // x starts in the first chunk.
        var x = chunkWidth
        // Area path start in (chunkWidth, canvasHeight).
        area.moveTo(x, canvasHeight)

        val firstValue = chunks.firstOrNull() ?: 0f
        var y = (canvasHeight * (maxValue - firstValue)) / chunkHeightDivision
        // Stroke path starts in the first value in chart.
        path.moveTo(x, y)
        // Line to first point of chart.
        area.lineTo(x, y)
        chunks.forEach { value ->
            y = (canvasHeight * (maxValue - value)) / chunkHeightDivision
            path.lineTo(x, y)
            area.lineTo(x, y)
            x += chunkWidth
        }
        // Draw stroke path.
        drawPath(
            path = path,
            style = Stroke(
                width = 8f
            ),
            color = chartStrokeLight
        )
        // Close area path.
        area.lineTo(chunkWidth * chunks.size, canvasHeight)
        // Draw area path.
        drawPath(
            path = area,
            brush = Brush.linearGradient(
                colors = chartLinearGradientLight,
                start = Offset(canvasWidth / 2, 0f),
                end = Offset(canvasWidth / 2, canvasHeight)
            )
        )
        // Draw selected Chunk dot.
        val selectedX = chunkWidth * (selected + 1)
        val selectedValue = chunks.getOrNull(selected) ?: 0f
        val selectedY =
            (canvasHeight * (maxValue - selectedValue)) / chunkHeightDivision
        drawCircle(
            color = chartStrokeLight,
            radius = 16f,
            center = Offset(selectedX, selectedY)
        )
    }
}

@Composable
fun HourlyForecastItem(
    hourlyForecastChunk: HourlyForecastChunk,
    selected: Boolean,
    index: Int,
    isNight: Boolean,
    userPreferencesTemperatureUnit: TemperatureUnit,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(64.dp)
            .height(96.dp)
            .clickable { onClick(index) }
            .background(
                color = if (selected) MaterialTheme.colors.selector else Color.Transparent,
                shape = selectorShape
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val drawableResId =
            if (isNight)
                hourlyForecastChunk.weather.nightDrawableResId
            else
                hourlyForecastChunk.weather.drawableResId
        Image(
            painterResource(id = drawableResId),
            contentDescription = "",
            modifier = Modifier.size(32.dp)
        )
        Text(
            hourlyForecastChunk.temperature.getStringAs(userPreferencesTemperatureUnit),
            color = MaterialTheme.colors.onSurface
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                formatMillis(hourlyForecastChunk.timestamp),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(widthDp = 360)
@Composable
fun HourlyForecastCardPreview() {
    WeatherTheme {
        citiesHourlyForecast.firstOrNull()?.let {
            HourlyForecastCard(it, false, TemperatureUnit.CELSIUS)
        }
    }
}

@Preview(widthDp = 360)
@Composable
fun HourlyForecastItemPreview() {
    WeatherTheme {
        HourlyForecastItem(
            hourlyForecastChunk = HourlyForecastChunk(
                Temperature(21f, TemperatureUnit.CELSIUS),
                50400000,
                Weather.HEAVY_RAIN
            ),
            false,
            0,
            false,
            TemperatureUnit.CELSIUS
        ) {}
    }
}
