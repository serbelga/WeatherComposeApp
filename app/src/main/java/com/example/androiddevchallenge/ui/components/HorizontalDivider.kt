package com.example.androiddevchallenge.ui.components

import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.outline

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier
) {
    Divider(thickness = 1.dp, color = MaterialTheme.colors.outline, modifier = modifier)
}