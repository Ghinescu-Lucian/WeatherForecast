package com.example.weatherapp.data.local.cache.json.dtos

import com.google.gson.annotations.SerializedName

data class WeatherTypeDto (
    @SerializedName("weatherDesc") val weatherDesc: String,
    @SerializedName("iconRes") val iconRes: Int
)
