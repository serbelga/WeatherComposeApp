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

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

val metropolisFontMedium = Font(R.font.metropolis_medium, FontWeight.Medium)
val metropolisFontNormal = Font(R.font.metropolis_regular, FontWeight.Normal)

val metropolisFontFamily = FontFamily(
    metropolisFontMedium, metropolisFontNormal
)

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = metropolisFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = metropolisFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h5 = TextStyle(
        fontFamily = metropolisFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = metropolisFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    caption = TextStyle(
        fontFamily = metropolisFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
