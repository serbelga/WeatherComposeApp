package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SettingsScreen(navigateUp: () -> Unit) {
    val temperatureUnits = enumValues<TemperatureUnit>()
    val (selectedTemperatureUnit, onTemperatureUnitSelected) = remember { mutableStateOf(temperatureUnits.getOrNull(0)) }
    Scaffold(
        topBar = {
            SettingsTopAppBar(navigationIconClick = navigateUp)
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        Column {
            // TODO Move to stringRes
            Text(
                "TEMPERATURE UNIT",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp)
                    .padding(start = 16.dp, bottom = 16.dp)
            )
            LazyColumn {
                items(temperatureUnits) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (it == selectedTemperatureUnit),
                                onClick = { onTemperatureUnitSelected(it) }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (it == selectedTemperatureUnit),
                            onClick = { onTemperatureUnitSelected(it) }
                        )
                        Text(
                            text = "${it.description} (${it.symbol})",
                            style = MaterialTheme.typography.body1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            Text(
                "WIND SPEED",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp)
                    .padding(start = 16.dp, bottom = 16.dp)
            )
            LazyColumn {
                items(temperatureUnits) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (it == selectedTemperatureUnit),
                                onClick = { onTemperatureUnitSelected(it) }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (it == selectedTemperatureUnit),
                            onClick = { onTemperatureUnitSelected(it) }
                        )
                        Text(
                            text = "${it.description} (${it.symbol})",
                            style = MaterialTheme.typography.body1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsTopAppBar(navigationIconClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigationIconClick) {
                Icon(Icons.Rounded.KeyboardBackspace, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        title = {
            Text(text = stringResource(id = R.string.settings))
        },
        elevation = 0.dp
    )
}

@Composable
@Preview(widthDp = 360)
fun SettingsTopAppBarPreview() {
    WeatherTheme {
        SettingsTopAppBar {

        }
    }
}

@Composable
@Preview(widthDp = 360)
fun SettingsTopAppBarDarkPreview() {
    WeatherTheme(true) {
        SettingsTopAppBar {

        }
    }
}

@Composable
@Preview(widthDp = 360)
fun SettingsScreenPreview() {
    WeatherTheme {
        SettingsScreen {

        }
    }
}
