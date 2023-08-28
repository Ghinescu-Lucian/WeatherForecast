package com.example.weatherapp.data.remote.visualCrossing.dto

import com.squareup.moshi.Json

data class VisualCrossingHourDataDto (
    @field:Json(name = "datetime")
    val time : String,
    @field:Json(name = "temp")
    val temperature: Double,
    @field:Json(name = "icon")
    val weatherCode: String,
    @field: Json(name = "conditions")
    val description: String,
    @field:Json(name = "pressure")
    val pressure: Double,
    @field:Json(name= "windspeed")
    val windSpeed: Double,
    @field:Json(name = "humidity")
    val humidity: Double,

)
