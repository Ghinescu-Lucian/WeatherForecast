package com.example.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDays: List<WeatherDataPerDay>,
    val currentWeatherData: WeatherData?,
    val partialResult: Boolean = false
)