package com.example.weatherapp.ui.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.maps.Map
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.time.LocalDateTime
import kotlin.collections.Map as Map1

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state : WeatherState
){
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Under construction Profile screen")
        Map(points = listOf())


           }


    }
@Composable
@Preview
fun PreviewProfileScreen(){

    val state = WeatherState(
        cityName = "Timisoara",
        weatherInfo = WeatherInfo(
            currentWeatherData = WeatherData(
                time = LocalDateTime.now(),
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
                            time = LocalDateTime.now(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(1),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(56),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(75),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
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
                 forecasts =    listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(2),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
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
                   forecasts =  listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(45),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
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
                   forecasts =  listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
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
                            time = LocalDateTime.now(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
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
        ProfileScreen( state = state)
    }
}