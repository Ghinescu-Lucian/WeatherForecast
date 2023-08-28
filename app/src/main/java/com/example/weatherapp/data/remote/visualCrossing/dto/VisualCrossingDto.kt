package com.example.weatherapp.data.remote.visualCrossing.dto

import com.squareup.moshi.Json

data class VisualCrossingDto (
    @field: Json(name = "days")
    val weatherData: List<VisualCrossingDaysDataDto>
)