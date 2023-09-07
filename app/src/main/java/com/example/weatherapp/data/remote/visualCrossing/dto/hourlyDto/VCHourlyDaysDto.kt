package com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto

import com.squareup.moshi.Json

data class VCHourlyDaysDto(
    @field: Json(name = "datetime")
    val date : String,
    @field: Json(name = "hours")
    val hours: List<VCHourlyDto>
)
