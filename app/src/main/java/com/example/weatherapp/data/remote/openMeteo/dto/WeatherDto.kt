package com.example.weatherapp.data.remote.openMeteo.dto

import com.squareup.moshi.Json

data class WeatherDto(

    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
