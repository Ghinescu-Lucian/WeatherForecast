package com.example.weatherapp.data.remote.accuWeather.dto.currentWeather

import com.example.weatherapp.data.remote.accuWeather.dto.PropertyDto
import com.google.gson.annotations.SerializedName

data class UnitDto(
    @SerializedName("Metric")
    val unit: PropertyDto
)
