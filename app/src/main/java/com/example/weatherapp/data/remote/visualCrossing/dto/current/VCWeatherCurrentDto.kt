package com.example.weatherapp.data.remote.visualCrossing.dto.current

import com.squareup.moshi.Json

data class VCWeatherCurrentDto (
    @field: Json(name = "datetime")
    val date: String,
)
