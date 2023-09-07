package com.example.weatherapp.data.remote.visualCrossing.dto.current

import com.squareup.moshi.Json

data class VCWeatherConditionsDto(
    @field: Json(name = "datetime")
    val time: String,
    @field: Json(name = "temp")
    val temperature: Double,
    @field: Json(name = "windspeed")
    val windspeed: Double,
    @field: Json(name ="pressure")
    val pressure: Double,
    @field: Json(name ="huidity")
    val humidity: Double,
    @field: Json(name = "conditions")
    val description: String,
    @field: Json(name = "icon")
    val weatherCode:String
)
