package com.example.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: List<WeatherDataPerDay>,
    val currentWeatherData: WeatherData?
)