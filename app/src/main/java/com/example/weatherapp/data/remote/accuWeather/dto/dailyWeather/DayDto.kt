package com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("Icon")
    val weatherCode: Int,
    @SerializedName("IconPhrase")
    val description: String
)
