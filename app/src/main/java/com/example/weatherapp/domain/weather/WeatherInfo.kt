package com.example.weatherapp.domain.weather

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    val weatherDataPerDays: List<WeatherDataPerDay>,
    val currentWeatherData: WeatherData?,
    val partialResult: Boolean = false
)