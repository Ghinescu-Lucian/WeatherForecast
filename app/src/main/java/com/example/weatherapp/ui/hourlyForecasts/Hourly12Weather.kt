package com.example.weatherapp.ui.hourlyForecasts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.serialization.json.JsonNull.content
import java.time.LocalDateTime

@Composable
fun Hourly12Weather(
    modifier: Modifier = Modifier,
    data: List<WeatherData>,
    current: Int = 0
){

    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = current){
        if(current > 2)
            scrollState.scrollToItem(current - 2)
        else scrollState.scrollToItem(0)
    }

    val dataCurrent = data.getOrNull(current) ?: data.lastOrNull() ?: return

    Column( modifier = modifier) {
        CurrentHour(data = dataCurrent)
        LazyColumn(
            state = scrollState,
            content = {
                items(data) { weatherData ->
                    RowElement(weatherData = weatherData)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
@Preview
fun Hourly12WeatherPreview(){
    val data =  WeatherDataPerDay(
        day =0,
        forecasts = listOf(
            WeatherData(
                time = LocalDateTime.now().toString(),
                temperature = 25.2,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(0),
                windSpeed = 12.0
            ),
            WeatherData(
                time = LocalDateTime.now().toString(),
                temperature = 25.12,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(1),
                windSpeed = 12.0
            ),
            WeatherData(
                time = LocalDateTime.now().toString(),
                temperature = 25.12,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(2),
                windSpeed = 12.0
            ),
            WeatherData(
                time = LocalDateTime.now().toString(),
                temperature = 25.12,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(3),
                windSpeed = 12.0
            ),
            WeatherData(
                time = LocalDateTime.now().toString(),
                temperature = 25.12,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(45),
                windSpeed = 12.0
            )

        )
    )
    WeatherAppTheme {
        Hourly12Weather(data = data.forecasts)
    }
}
