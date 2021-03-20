package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

enum class Weather(
    val value: String,
    @DrawableRes val drawableResId: Int
) {
    SUNNY("Sunny", R.drawable.sunny),
    CLOUDY("Cloudy", R.drawable.cloudy),
    RAINY("Rainy", R.drawable.rainy),
    LIGHT_RAIN("Light Rain", R.drawable.light_rain),
    MODERATE_RAIN("Moderate Rain", R.drawable.moderate_rain),
    HEAVY_RAIN("Heavy Rain", R.drawable.heavy_rain),
    SNOWY("Snowy", R.drawable.snowy),
    STORMY("Stormy", R.drawable.stormy),
    WINDY("Windy", R.drawable.windy)
}