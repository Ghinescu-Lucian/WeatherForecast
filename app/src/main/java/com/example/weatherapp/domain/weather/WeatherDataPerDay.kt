package com.example.weatherapp.domain.weather

data class WeatherDataPerDay(
    val day: Int,
    val forecasts:List<WeatherData>
)
