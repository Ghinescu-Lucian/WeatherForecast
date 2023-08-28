package com.example.weatherapp.data.remote.accuWeather.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class CurrentWeatherDto(
    @SerializedName("DateTime")
    val time: String,
    @SerializedName("WeatherIcon")
    val type: Int,
    @SerializedName("IconPhrase")
    val description: String,
    @SerializedName("Temperature")
    val temperature: PropertyDto,
    @SerializedName("Wind")
    val wind: WindDto,
    @SerializedName("RelativeHumidity")
    val humidity: Double
)
