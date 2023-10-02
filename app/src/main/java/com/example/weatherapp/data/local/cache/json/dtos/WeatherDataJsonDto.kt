package com.example.weatherapp.data.local.cache.json.dtos

import com.google.gson.annotations.SerializedName

data class WeatherDataJsonDto (
    @SerializedName("time")
    val time: String,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("windSpeed")
    val windSpeed: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("isDay")
    val isDay: Boolean = true,
    @SerializedName("weatherType")
    val weatherType: WeatherTypeDto
)