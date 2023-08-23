package com.example.weatherapp.ui

import com.example.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val cityName: String? = null,
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error:String? = null
)
