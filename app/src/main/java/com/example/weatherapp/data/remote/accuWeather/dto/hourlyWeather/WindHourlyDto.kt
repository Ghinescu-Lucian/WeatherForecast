package com.example.weatherapp.data.remote.accuWeather.dto.hourlyWeather

import com.example.weatherapp.data.remote.accuWeather.dto.PropertyDto
import com.google.gson.annotations.SerializedName

data class WindHourlyDto(
    @SerializedName("Speed")
    val speed: PropertyDto
)
