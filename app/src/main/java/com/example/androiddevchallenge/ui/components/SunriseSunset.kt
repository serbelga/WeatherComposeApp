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

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.CitySunriseSunset
import com.example.androiddevchallenge.model.valenciaSunriseSunset
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.util.DateUtil.formatMillis
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SunriseSunsetCard(sunriseSunset: CitySunriseSunset) {
    WeatherCard {
        CardTitle(stringResource(id = R.string.sunrise_sunset))
        Column {
            Text(formatMillis(sunriseSunset.sunriseTimestamp))
            Text(formatMillis(sunriseSunset.sunsetTimestamp))
        }
    }
}

@Preview(widthDp = 360)
@Composable
fun SunriseSunsetCardPreview() {
    WeatherTheme {
        SunriseSunsetCard(valenciaSunriseSunset)
    }
}