package com.example.androiddevchallenge.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.CityWeather
import com.example.androiddevchallenge.model.DailyForecast
import com.example.androiddevchallenge.model.Temperature
import com.example.androiddevchallenge.model.TemperatureUnit
import com.example.androiddevchallenge.model.Weather
import com.example.androiddevchallenge.model.dailyForecastList
import com.example.androiddevchallenge.ui.components.HorizontalDivider
import com.example.androiddevchallenge.ui.theme.WeatherTheme
import com.example.androiddevchallenge.ui.theme.outline
import com.google.accompanist.insets.statusBarsPadding
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherScreen(cityWeather: CityWeather) {
    val scrollState = rememberScrollState(0)
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.surface),
                title = {
                    Column(
                        modifier = Modifier.padding(start = 24.dp, top = 8.dp)
                    ) {
                        // TODO 750 as const
                        if (scrollState.value >= 750) {
                            Text(
                                text = cityWeather.city.name,
                                style = MaterialTheme.typography.h6
                            )
                        } else {
                            Text(
                                text = cityWeather.city.name,
                                style = MaterialTheme.typography.h5
                            )
                        }
                        AnimatedVisibility(visible = scrollState.value >= 750) {
                            Text(
                                text = cityWeather.temperature.toString()
                            )
                        }
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            if (scrollState.value >= 750) {
                                Text(
                                    text = "Wednesday, 13:00h",
                                    style = MaterialTheme.typography.caption,
                                )
                            } else {
                                Text(
                                    text = "Wednesday, 13:00h",
                                    style = MaterialTheme.typography.body1,
                                )
                            }
                        }
                    }
                },
                actions = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Tune,
                                contentDescription = "Settings"
                            )
                        }
                    }
                },
                modifier = Modifier.height(96.dp)
            )
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
            // .background(Color(0xFFE8EAF6))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 64.dp, top = 48.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.rain_cloud),
                    contentDescription = "",
                    modifier = Modifier
                        .height(160.dp)
                        .width(200.dp)
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        cityWeather.weather.value,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Text(
                    text = cityWeather.temperature.toString(),
                    style = MaterialTheme.typography.h3
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Icon(
                            Icons.Rounded.Air,
                            "Wind speed"
                        )
                    }
                    Text(
                        text = cityWeather.getWindSpeed()
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Icon(
                            painterResource(id = R.drawable.ic_water_outline_24),
                            "Wind"
                        )
                    }
                    Text(text = cityWeather.getHumidity())
                }
            }
            HourlyForecastCard(20f, 25f)
            DailyForecastCard()
            SunriseSunsetCard()
        }
        if (scrollState.value >= 175) {
            HorizontalDivider()
        }
    }
}

@Composable
fun HourlyForecastCard(minValue: Float, maxValue: Float) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.outline),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            CardTitle("Hourly forecast")
            LazyRow(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                items(predictionHours) {
                    HourlyForecastItem(predictionHour = it)
                }
            }
            HourlyForecastChart(
                minValue = minValue,
                maxValue = maxValue,
                arrayListOf(21f, 22f, 22f, 23f, 24f, 24f, 23f, 22f, 22f, 21f, 21f)
            )
        }
    }
}

@Composable
fun HourlyForecastChart(minValue: Float, maxValue: Float, temperatureList: List<Float>) {
    val chunkHeightDivision = maxValue - minValue
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val chunkWidth = canvasWidth / temperatureList.size
        val path = Path()
        val area = Path()
        path.moveTo(0f, canvasHeight)
        area.moveTo(0f, canvasHeight)
        temperatureList.forEachIndexed { index, fl ->
            val x = chunkWidth * (index + 1)
            val y = (canvasHeight * (maxValue - fl)) / chunkHeightDivision
            path.lineTo(x, y)
            area.lineTo(x, y)
        }
        drawPath(
            path = path,
            style = Stroke(
                width = 8f
            ),
            color = Color(0xFFFFB74D)
        )
        area.lineTo(chunkWidth * temperatureList.size, canvasWidth)
        drawPath(
            path = area,
            brush = linearGradient(
                colors = listOf(Color(0xFFFFD54F), Color.Transparent),
                start = Offset(canvasWidth / 2, 0f),
                end = Offset(canvasWidth / 2, canvasHeight)
            )
        )
    }
}

@Composable
fun HourlyForecastItem(predictionHour: HourlyForecast) {
    Column(
        modifier = Modifier.width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Cloud, contentDescription = "")
        Text(predictionHour.temperature)
        Text(predictionHour.time)
    }
}

@Composable
fun DailyForecastCard() {
    Card(
        elevation = 0.dp,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.outline),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            CardTitle("Daily forecast")
            dailyForecastList.forEach {
                DailyForecastItem(dailyForecast = it)
            }
        }
    }
}

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
fun SunriseSunsetCard() {
    Card(
        elevation = 0.dp,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.outline),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp)
    ) {
        CardTitle("Sunrise & Sunset")
    }
}

// TODO: 18/03/2021 Update data class
data class HourlyForecast(
    val temperature: String,
    val time: String
)

val predictionHours = listOf(
    HourlyForecast("21º", "14:00h"),
    HourlyForecast("21.5º", "15:00h"),
    HourlyForecast("22.5º", "16:00h"),
    HourlyForecast("21.5º", "17:00h"),
    HourlyForecast("21º", "18:00h")
)

@Composable
fun DailyForecastItem(dailyForecast: DailyForecast) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            dailyForecast.day,
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = dailyForecast.temperatureMax.toString()
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = dailyForecast.temperatureMin.toString(),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Image(
            painterResource(id = dailyForecast.weather.drawableResId),
            contentDescription = "",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview(widthDp = 360)
@Composable
fun DailyForecastItemPreview() {
    WeatherTheme {
        DailyForecastItem(
            DailyForecast(
                "Tomorrow",
                Temperature(27.0, TemperatureUnit.CELSIUS),
                Temperature(21.0, TemperatureUnit.CELSIUS),
                Weather.SUNNY
            )
        )
    }
}

/*@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    WeatherTheme {
        WeatherScreen(valenciaWeather)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    WeatherTheme(darkTheme = true) {
        WeatherScreen(valenciaWeather)
    }
}*/
