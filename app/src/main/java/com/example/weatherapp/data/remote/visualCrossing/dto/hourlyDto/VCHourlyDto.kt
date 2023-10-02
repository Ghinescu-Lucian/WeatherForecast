package com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto

import com.squareup.moshi.Json

data class VCHourlyDto(
    @field: Json(name="datetime")
    val hour: String,
    @field: Json(name="temp")
    val temperature: Double,
    @field: Json(name="conditions")
    val description: String,
    @field: Json(name = "icon")
    val weatherCode: String,
    @field: Json(name = "pressure")
    val pressure: Double,
    @field: Json(name = "windspeed")
    val windSpeed: Double,
    @field: Json(name = "humidity")
    val humidity: Double

)
