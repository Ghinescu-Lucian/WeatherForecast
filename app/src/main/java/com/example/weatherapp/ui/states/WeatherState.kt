package com.example.weatherapp.ui.states

import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.ui.model.mapper.WeatherInfoUI

data class WeatherState(
    val cityName: String? = null,
//    val weatherInfo: WeatherInfoUI? = null,
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error:String? = null
)
