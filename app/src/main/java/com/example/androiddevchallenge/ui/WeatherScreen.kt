package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.model.CityWeather
import com.example.androiddevchallenge.ui.model.valenciaWeather
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun WeatherScreen(cityWeather: CityWeather) {
    val scrollState = rememberScrollState(0)
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp, top = 8.dp)
                ) {
                    Text(
                        text = cityWeather.city.name,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = "Wednesday, 13:00h",
                        style = MaterialTheme.typography.body1,
                    )
                }
                IconButton(
                    onClick = {  },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        Icons.Rounded.Tune,
                        contentDescription = "Settings"
                    )
                }
            }
        },
        backgroundColor = Color(0xFF2196F3)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 64.dp)
            ) {
                Icon(
                    Icons.Default.Brightness5,
                    contentDescription = "",
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                )
                Text(cityWeather.weather.value, style = MaterialTheme.typography.body1)
                Text(
                    text = cityWeather.temperature.toString(),
                    style = MaterialTheme.typography.h3
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Rounded.Air,
                        "Wind speed"
                    )
                    Text(text = cityWeather.getWindSpeed())
                    Icon(
                        painterResource(id = R.drawable.ic_water_outline_24),
                        "Wind"
                    )
                    Text(text = cityWeather.getHumidity())
                }
            }
            HourlyForecastCard()
            HourlyForecastCard()
            HourlyForecastCard()
            HourlyForecastCard()
            // TODO Prediction next days
            // TODO Sun hours
        }
    }
}

@Composable
fun HourlyForecastCard() {
    Card(
        elevation = 0.dp,
        backgroundColor = Color(0x4DFFFFFF),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            LazyRow {
                items(predictionHours) {
                    HourlyForecastItem(predictionHour = it)
                }
            }
            val max = 23f
            val min = 20f
            val chunkHeightDivision = max - min
            val x0 = 0f
            Canvas(modifier = Modifier.fillMaxWidth().height(120.dp)) {
                val canvasWidth = size.width
                val chunkWidth = canvasWidth / 5
                val canvasHeight = size.height

                val y1 = (canvasHeight * (max - 21f)) / chunkHeightDivision
                val y2 = (canvasHeight * (max - 21.5f)) / chunkHeightDivision
                val y3 = (canvasHeight * (max - 22f)) / chunkHeightDivision
                val y4 = (canvasHeight * (max - 21.5f)) / chunkHeightDivision
                val y5 = (canvasHeight * (max - 21f)) / chunkHeightDivision
                drawPath(
                    path = Path().apply {
                        moveTo(x0, canvasHeight)
                        lineTo(chunkWidth, y1)
                        lineTo(chunkWidth * 2, y2)
                        lineTo(chunkWidth * 3, y3)
                        lineTo(chunkWidth * 4, y4)
                        lineTo(chunkWidth * 5, y5)
                        lineTo(chunkWidth * 6, canvasHeight)
                    },
                    color = Color(0xFF3F51B5),
                    style = Stroke(
                        width = 8f
                    )
                )
            }
        }
        // TODO Graph
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

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        WeatherScreen(valenciaWeather)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        WeatherScreen(valenciaWeather)
    }
}
