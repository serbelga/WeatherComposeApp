package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.outline
import java.util.*

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
