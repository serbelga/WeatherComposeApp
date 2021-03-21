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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.util.DateUtil.formatMillis

@Composable
fun SunriseSunsetCard(
    sunriseMillis: Long,
    sunsetMillis: Long
) {
    WeatherCard {
        CardTitle(stringResource(id = R.string.sunrise_sunset))
        Column {
            Text(formatMillis(sunriseMillis))
            Text(formatMillis(sunsetMillis))
        }
        Canvas(
            modifier = Modifier
                .height(160.dp)
                .background(Color.LightGray)
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
        }
    }
}

@Preview(widthDp = 360)
@Composable
fun SunriseSunsetCardPreview() {
    WeatherTheme {
        SunriseSunsetCard(
            sunriseMillis = 25200000,
            sunsetMillis = 68640000
        )
    }
}