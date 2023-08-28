package com.example.weatherapp.data.remote.accuWeather.dto

import com.google.gson.annotations.SerializedName

data class PropertyDto(
    @SerializedName("Value")
    val value: Double,
)