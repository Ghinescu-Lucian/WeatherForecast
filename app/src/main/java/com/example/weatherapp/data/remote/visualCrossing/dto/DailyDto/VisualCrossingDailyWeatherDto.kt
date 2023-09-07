package com.example.weatherapp.data.remote.visualCrossing.dto.DailyDto

import com.squareup.moshi.Json

data class VisualCrossingDailyWeatherDto(
    @field:Json(name = "datetime")
    val time : String,
    @field:Json(name = "tempmax")
    val temperatureMax: Double,
    @field:Json(name = "tempmin")
    val temperatureMin: Double,
    @field:Json(name = "icon")
    val weatherCode: String,
    @field: Json(name = "description")
    val description: String,
    @field:Json(name = "pressure")
    val pressure: Double,
    @field:Json(name= "windspeed")
    val windSpeed: Double,
    @field:Json(name = "humidity")
    val humidity: Double,
    @field: Json(name ="sunrise")
    val sunrise:String,
    @field: Json(name="sunset")
    val sunset: String
)
