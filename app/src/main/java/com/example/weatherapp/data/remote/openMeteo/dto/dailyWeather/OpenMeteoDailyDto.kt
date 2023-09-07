package com.example.weatherapp.data.remote.openMeteo.dto.dailyWeather

import com.squareup.moshi.Json

data class OpenMeteoDailyDto(
    @field:Json(name = "daily")
    val days: OpenMeteoDailyWeatherDto
)
