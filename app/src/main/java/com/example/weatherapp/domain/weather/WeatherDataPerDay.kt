package com.example.weatherapp.domain.weather

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataPerDay(
    var day: Int,
    val minTemperature: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val sunRise: String = "",
    val sunSet: String = "",
    val moonRise: String = "",
    val moonSet: String = "",
    val weatherTypeDay: WeatherType = WeatherType.ClearSky,
    val weatherTypeNight: WeatherType = WeatherType.ClearSky,
    val forecasts:List<WeatherData>
)
