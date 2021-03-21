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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.ui.theme.sunChartStroke
import com.example.androiddevchallenge.util.DateUtil.formatMillis
import java.lang.Math.toDegrees
import kotlin.math.PI

@Composable
fun SunriseSunsetCard(
    timestamp: Long,
    sunriseMillis: Long,
    sunsetMillis: Long
) {
    WeatherCard {
        Column {
            CardTitle(stringResource(id = R.string.sunrise_sunset))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(
                    modifier = Modifier
                        .height(100.dp)
                        .width(240.dp)
                        .clipToBounds()
                ) {
                    val canvasHeight = size.height
                    val canvasWidth = size.width
                    val mX = canvasWidth / 2
                    val mY = canvasHeight
                    val radius = 240f
                    val oval = Rect(
                        mX - radius,
                        mY - radius,
                        mX + radius,
                        mY + radius
                    )
                    val progressBar = Path().apply {
                        arcTo(
                            oval,
                            180f,
                            180f,
                            false
                        )
                    }
                    drawPath(
                        progressBar,
                        color = Color.Gray,
                        style = Stroke(
                            width = 16f,
                            pathEffect = dashPathEffect(
                                floatArrayOf(10f, 10f), 0f
                            )
                        )
                    )
                    val radians = ((timestamp - sunriseMillis) * PI) / (sunsetMillis - sunriseMillis)
                    val progress = Path().apply {
                        arcTo(
                            oval,
                            180f,
                            toDegrees(radians).toFloat(),
                            false
                        )
                    }
                    drawPath(
                        progress,
                        color = sunChartStroke,
                        style = Stroke(
                            width = 16f,
                            pathEffect = dashPathEffect(
                                floatArrayOf(10f, 10f), 0f
                            )
                        )
                    )
                }
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .width(240.dp)
                        .padding(top = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(formatMillis(sunriseMillis))
                    Text(formatMillis(sunsetMillis))
                }
            }
        }
    }
}

@Preview(widthDp = 360)
@Composable
fun SunriseSunsetCardPreview() {
    WeatherTheme {
        SunriseSunsetCard(
            timestamp = 46800000,
            sunriseMillis = 25200000,
            sunsetMillis = 68640000
        )
    }
}