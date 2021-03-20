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
package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

enum class Weather(
    @StringRes val stringResId: Int,
    @DrawableRes val drawableResId: Int,
    @DrawableRes val nightDrawableResId: Int = drawableResId,
) {
    // TODO Move to string resources
    CLEAR(R.string.clear, R.drawable.sunny, R.drawable.moon),
    CLOUDY(R.string.cloudy, R.drawable.cloudy),
    RAINY(R.string.rainy, R.drawable.rainy),
    LIGHT_RAIN(R.string.light_rain, R.drawable.light_rain),
    MODERATE_RAIN(R.string.moderate_rain, R.drawable.moderate_rain),
    HEAVY_RAIN(R.string.heavy_rain, R.drawable.heavy_rain),
    SNOWY(R.string.snowy, R.drawable.snowy),
    STORMY(R.string.stormy, R.drawable.stormy),
    WINDY(R.string.windy, R.drawable.windy),
    PARTLY_CLOUDY(R.string.partly_cloudy, R.drawable.partly_cloudy)
}