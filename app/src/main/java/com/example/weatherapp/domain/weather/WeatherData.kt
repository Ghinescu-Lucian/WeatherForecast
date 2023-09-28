package com.example.weatherapp.domain.weather

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
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