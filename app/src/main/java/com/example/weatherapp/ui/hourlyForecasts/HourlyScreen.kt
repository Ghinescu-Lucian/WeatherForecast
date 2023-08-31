package com.example.weatherapp.ui.hourlyForecasts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDateTime

@Composable
fun HourlyScreen(
    modifier : Modifier = Modifier,
    state: WeatherState,
    onClickSeeCurrent: () ->Unit ={},
){
    Column(
        modifier = modifier
    )
    {
        Title(
            city = state.cityName,
            modifier = Modifier
        )
        Hourly12Weather(
            data = state.weatherInfo!!.weatherDataPerDay[0].forecasts
        )

    }

}

@Composable
@Preview
fun HourlyScreenPreview(){

val state = WeatherState(
    cityName = "Timisoara",
    weatherInfo = WeatherInfo(
        currentWeatherData = WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 12.0
        ),
    weatherDataPerDay = listOf(
        WeatherDataPerDay(
            day =0,
            listOf(
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.12,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(1),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.12,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(2),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.12,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(3),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.12,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(45),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =1,
            listOf(
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(2),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(3),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =2,
            listOf(
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(45),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =3,
            listOf(
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =4,
            listOf(
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now(),
                    temperatureCelsius = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                )

            )
        ),
    )
    )
)
    WeatherAppTheme {
        HourlyScreen(state = state)
    }

}