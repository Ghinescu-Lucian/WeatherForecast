package com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather

import com.google.gson.annotations.SerializedName

data class CelestialDto(
    @SerializedName("Rise")
    val rise: String,
    @SerializedName("Set")
    val set: String,
)
