package com.example.weatherapp.ui.DailyForecasts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.states.WeatherState

@Composable
fun DailyScreen(
    modifier: Modifier,
    state: WeatherState,
    seeAllHourly: () -> Unit = {},
    onClickSeeCurrent: () ->Unit = {}
){
    Text(text = "Under construction Daily screen", modifier = modifier )
}