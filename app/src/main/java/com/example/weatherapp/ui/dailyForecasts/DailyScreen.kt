package com.example.weatherapp.ui.dailyForecasts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.hourlyForecasts.CurrentHour
import com.example.weatherapp.ui.hourlyForecasts.Hourly12Weather
import com.example.weatherapp.ui.hourlyForecasts.RowElement
import com.example.weatherapp.ui.hourlyForecasts.Title
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDateTime



@Composable
fun DailyScreen(
    modifier: Modifier,
    state: WeatherState,
//    seeAllHourly: () -> Unit = {},
//    onClickSeeCurrent: () ->Unit = {},
//    data: List<WeatherData>
){

//    val data = state.weatherInfo!!.weatherDataPerDay.get(0).forecasts
//    val data = state.weatherInfo!!.weatherDataPerDay[0].forecasts
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Under construction Daily screen", modifier = modifier )
        Image(
            painter = painterResource(id = R.drawable.ic_under_construction),
            contentDescription = "Under construction",
            modifier= Modifier.height(250.dp)
        )
        Title(
            city = state.cityName,
            modifier = Modifier
        )
//        Hourly12Weather(
//            data = state.weatherInfo!!.weatherDataPerDay[0].forecasts
//        )
        val points = listOf(
            listOf(0f,20f),
            listOf(1f, 23f),
            listOf(2f, 24f),
            listOf(3f, 22f),
            listOf(4f, 23f)

            )
        DailyGraph(data = points)

    }

}


@Composable
@Preview
fun DailyWeatherPreview(){
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
                            weatherType = WeatherType.fromWMO(56),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(75),
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
        DailyScreen(modifier = Modifier, state = state)
    }
}
