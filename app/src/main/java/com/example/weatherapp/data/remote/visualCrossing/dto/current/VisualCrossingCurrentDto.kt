package com.example.weatherapp.data.remote.visualCrossing.dto.current

import com.squareup.moshi.Json

data class VisualCrossingCurrentDto(
    @field: Json(name = "days")
    val day: List<VCWeatherCurrentDto>,
    @field: Json(name = "currentConditions")
    val currentConditions: VCWeatherConditionsDto
)
