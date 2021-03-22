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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.outline
import java.util.Locale

@Composable
fun CardTitle(text: String) {
    Text(
        text.toUpperCase(Locale.getDefault()),
        style = MaterialTheme.typography.caption,
        modifier = Modifier
            .paddingFromBaseline(top = 24.dp)
            .padding(start = 16.dp, bottom = 16.dp)
    )
}

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.outline),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        content = content
    )
}
