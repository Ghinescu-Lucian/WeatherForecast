package com.example.weatherapp.ui.hourlyForecasts

import android.util.Log
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
    Log.d("Hourly state :", state.toString())

    Column(
        modifier = modifier
    )
    {
        Title(
            city = state.cityName,
            modifier = Modifier
        )
        Log.d("State2", state.weatherInfo?.weatherDataPerDays.toString())

        Hourly12Weather(
                 data = state.weatherInfo!!.weatherDataPerDays.getOrNull(0)!!.forecasts
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
            time = LocalDateTime.now().toString(),
            temperature = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 12.0
        ),
    weatherDataPerDays = listOf(
        WeatherDataPerDay(
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
        ),
        WeatherDataPerDay(
            day =1,
           forecasts =  listOf(
                WeatherData(
                    time = LocalDateTime.now().toString(),
                    temperature = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(2),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now().toString(),
                    temperature = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(3),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =2,
          forecasts =   listOf(
                WeatherData(
                    time = LocalDateTime.now().toString(),
                    temperature = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(45),
                    windSpeed = 12.0
                ),
                WeatherData(
                    time = LocalDateTime.now().toString(),
                    temperature = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =3,
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
                    temperature = 25.2,
                    pressure = 1000.0,
                    humidity = 56.0,
                    weatherType = WeatherType.fromWMO(0),
                    windSpeed = 12.0
                )

            )
        ),
        WeatherDataPerDay(
            day =4,
           forecasts =  listOf(
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
                    temperature = 25.2,
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