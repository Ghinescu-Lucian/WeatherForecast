package com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto

import com.squareup.moshi.Json

data class VisualCrossingDto (
    @field: Json(name = "days")
    val weatherData: List<VisualCrossingDaysDataDto>
)