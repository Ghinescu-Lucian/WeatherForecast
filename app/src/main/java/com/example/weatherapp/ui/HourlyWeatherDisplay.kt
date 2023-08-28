package com.example.weatherapp.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractor
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractorImpl
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.ui.animation.radialGradient
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.time.format.DateTimeFormatter
@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
//    sa il pun in mapper
    val formattedTime = remember(weatherData) {
          weatherData.time.format(
                DateTimeFormatter.ofPattern("HH:mm")
            )

    }
    Column(
        modifier = modifier
            .background(radialGradient(colorStart = MaterialTheme.colorScheme.outlineVariant, colorEnd = MaterialTheme.colorScheme.outline)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            color = MaterialTheme.colorScheme.background
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun HourlyPreview(){
    WeatherAppTheme {


//
//        val interactor  = WeatherDataInteractorImpl()
//        val viewModel = WeatherViewModel(interactor = interactor)
//        val weatherState = viewModel.state.collectAsState()
//
//        weatherState.value.weatherInfo?.currentWeatherData?.let {
//            HourlyWeatherDisplay(weatherData = it)
//        }

//        val viewModel = WeatherViewModel()
//        viewModel.loadWeatherInfo()
//        viewModel.state?.weatherInfo?.currentWeatherData?.let { HourlyWeatherDisplay(weatherData = it) }
    }
}
