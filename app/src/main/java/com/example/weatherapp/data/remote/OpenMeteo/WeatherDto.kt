package com.example.weatherapp.data.remote.OpenMeteo

import com.squareup.moshi.Json

data class WeatherDto(

    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
