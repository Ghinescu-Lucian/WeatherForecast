package com.example.weatherapp.ui.hourlyForecasts

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.viewModels.WeatherViewModel
import java.time.LocalDateTime

@Composable
fun HourlyScreen(
    modifier : Modifier = Modifier,
    state: WeatherState,
    viewModel: WeatherViewModel = hiltViewModel()
){
    Log.d("Hourly state :", state.toString())

    Column {


        if(state.online!= null && !state.online){
            Text(
                "Offline mode", textAlign = TextAlign.Center,
                modifier = Modifier

                    .fillMaxWidth()
                    .background(Color(0xFFFD0000).copy(alpha = 0.95f)),
                fontSize = 14.sp,
                color = Color.White
//                style = TextStyle(background = Color.Red)
            )
        }

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
                data = state.weatherInfo!!.weatherDataPerDays.getOrNull(0)!!.forecasts,
                current = viewModel.getCurrentHour()
            )

        }
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