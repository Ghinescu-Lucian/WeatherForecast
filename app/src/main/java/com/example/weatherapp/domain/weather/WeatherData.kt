package com.example.weatherapp.domain.weather


data class WeatherData(
//    interfata Clock ce are now() -> long
    val time: String,
    val temperature: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val description: String = "",
    val isDay: Boolean = true,
    val weatherType: WeatherType
)