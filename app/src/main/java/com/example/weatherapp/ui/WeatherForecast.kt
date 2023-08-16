package com.example.weatherapp.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractorImpl
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
){
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let {data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

        ){
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))




            LazyRow(content = {

                items(data.prognoze) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(150.dp)
                            .padding(horizontal = 16.dp)
                    )
                }

            },

                modifier = Modifier.height( 250.dp)

                )
        }
    }

}

@Preview
@Composable
fun WeatherForecastPreview(){
    WeatherAppTheme {

        val interactor = WeatherDataInteractorImpl()
        val viewModel = WeatherViewModel(interactor = interactor)
        val state = viewModel.state.collectAsState()
        WeatherForecast(state = state.value)

//        val viewModel = WeatherViewModel()
//        viewModel.loadWeatherInfo()
//        val state = viewModel.state
//        WeatherForecast(state = state)
    }
}
