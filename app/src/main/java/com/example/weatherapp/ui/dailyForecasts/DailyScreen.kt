package com.example.weatherapp.ui.dailyForecasts

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.hourlyForecasts.Title
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.viewModels.DailyViewModel
import java.time.LocalDateTime


@Composable
fun DailyScreen(
    modifier: Modifier,
    state: WeatherState,
    viewModel: DailyViewModel = hiltViewModel()
//    seeAllHourly: () -> Unit = {},
//    onClickSeeCurrent: () ->Unit = {},
//    data: List<WeatherData>
){

    val data  = state.weatherInfo?.weatherDataPerDays?.let { viewModel.calculateTemperatureData(data = it) }
    val xLabels = viewModel.getDaysStrings(state, literal = false)
    Log.d("Daily Screen", state.weatherInfo?.weatherDataPerDays.toString())
    Log.d("Daily Screen", state.toString())

    Column{

        if(state.online != null && !state.online){
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
//        modifier =  Modifier.verticalScroll(state)
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
             .weight(weight = 1f, fill = false)

        ) {


//            Text(text = "Under construction Daily screen", modifier = modifier)
//        Image(
//            painter = painterResource(id = R.drawable.ic_under_construction),
//            contentDescription = "Under construction",
//            modifier= Modifier.height(250.dp)
//        )

        Title(
            city = state.cityName,
            modifier = Modifier
        )
        Box(modifier = Modifier.height(320.dp)) {

            ExpandableLazyColumn(state = state, viewModel = viewModel)

        }
        Text("Max. temperature evolution")
        if (data != null) {
            Box(modifier = Modifier.padding( start = 8.dp, end = 8.dp, bottom = 8.dp)) {
                DailyGraph(data = data.take(5), xLabels = xLabels)
            }
//            DailyGraph(data = points)
        }

//            val points = listOf(
//                listOf(0f, 20f),
//                listOf(1f, 30f),
//                listOf(2f, 20f),
//                listOf(3f, 30f),
//                listOf(4f, 35f)
//
//            )
    }


    }

}


@Composable
@Preview
fun DailyWeatherPreview(){
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
                            weatherType = WeatherType.fromWMO(56),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(75),
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
                   forecasts =  listOf(
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
        DailyScreen(modifier = Modifier, state = state, )
    }
}
