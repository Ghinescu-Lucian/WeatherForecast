package com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto

import com.squareup.moshi.Json

data class VisualCrossingHourlyDto(
    @field: Json(name= "days")
    val days: List<VCHourlyDaysDto>
)
