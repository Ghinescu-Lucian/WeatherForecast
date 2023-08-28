package com.example.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
//    interfata Clock ce are now() -> long
    val time: LocalDateTime, // sa fie String
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val description: String = "",
    val isDay: Boolean = true,
    val weatherType: WeatherType
)