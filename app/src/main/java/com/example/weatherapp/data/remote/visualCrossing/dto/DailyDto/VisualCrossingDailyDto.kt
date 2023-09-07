package com.example.weatherapp.data.remote.visualCrossing.dto.DailyDto

import com.squareup.moshi.Json

data class VisualCrossingDailyDto(
    @field: Json(name ="days" )
    val days: List<VisualCrossingDailyWeatherDto>
)
