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

@Composable
fun HourlyForecastCard(hourlyForecast: CityHourlyForecast) {
    var selected by remember { mutableStateOf(0) }
    WeatherCard {
        Column {
            CardTitle(stringResource(id = R.string.hourly_forecast))
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(hourlyForecast.chunks) { index, item ->
                    HourlyForecastItem(hourlyForecastChunk = item, selected == index, index) {
                        selected = it
                    }
                }
            }
            HourlyForecastChart(
                minValue = hourlyForecast.minValue,
                maxValue = hourlyForecast.maxValue,
                selected = selected,
                hourlyForecast.chunks.map { it.temperature.value }
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
    val chunkHeightDivision = if (maxValue > minValue) maxValue - minValue else 1f
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val chunkWidth = canvasWidth / (chunks.size + 1)
        val path = Path()
        val area = Path()
        path.moveTo(0f, canvasHeight)
        area.moveTo(0f, canvasHeight)
        chunks.forEachIndexed { index, value ->
            val x = chunkWidth * index
            val y = (canvasHeight * (maxValue - value)) / chunkHeightDivision
            path.lineTo(x, y)
            area.lineTo(x, y)
        }
        drawPath(
            path = path,
            style = Stroke(
                width = 8f
            ),
            color = chartStrokeLight
        )
        area.lineTo(chunkWidth * chunks.size, canvasWidth)
        drawPath(
            path = area,
            brush = Brush.linearGradient(
                colors = chartLinearGradientLight,
                start = Offset(canvasWidth / 2, 0f),
                end = Offset(canvasWidth / 2, canvasHeight)
            )
        )
        val x = chunkWidth * selected
        val y =
            (canvasHeight * (maxValue - chunks.getOrElse(selected) { 0f })) / chunkHeightDivision
        drawCircle(
            color = chartStrokeLight,
            radius = 16f,
            center = Offset(x, y)
        )
    }
}

@Composable
fun HourlyForecastItem(
    hourlyForecastChunk: HourlyForecastChunk,
    selected: Boolean,
    index: Int,
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
        Image(
            painterResource(id = hourlyForecastChunk.weather.drawableResId),
            contentDescription = "",
            modifier = Modifier.size(32.dp)
        )
        Text(
            hourlyForecastChunk.temperature.toString(),
            color = MaterialTheme.colors.onSurface
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                hourlyForecastChunk.time,
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
            HourlyForecastCard(it)
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
                "14:00h",
                Weather.HEAVY_RAIN
            ),
            false, 0
        ) {}
    }
}
