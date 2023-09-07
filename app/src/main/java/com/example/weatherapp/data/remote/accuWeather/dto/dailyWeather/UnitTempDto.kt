package com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather

import com.google.gson.annotations.SerializedName

data class UnitTempDto(
    @SerializedName("Value")
    val value: Double
)
