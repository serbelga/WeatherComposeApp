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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = indigoA200,
    secondary = indigo200,
    background = backgroundDark,
    surface = backgroundDark
)

private val LightColorPalette = lightColors(
    primary = indigoA400,
    secondary = indigoA700,
    onSurface = Color.Black,
    onBackground = Color(0xFF292929),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFFBFBFB)
)

val Colors.outline: Color
    @Composable get() = if (isLight) outlineLight else outlineDark

val Colors.selector: Color
    @Composable get() = if (isLight) selectorLight else selectorDark

val Colors.chartStroke: Color
    @Composable get() = if (isLight) chartStrokeLight else chartStrokeLight

val Colors.chartFill: Color
    @Composable get() = if (isLight) chartFillLight else chartFillLight

@Composable
fun WeatherTheme(darkTheme: Boolean = false, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
