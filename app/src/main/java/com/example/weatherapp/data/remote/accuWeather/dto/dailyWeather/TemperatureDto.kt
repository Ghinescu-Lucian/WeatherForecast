package com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather

import com.google.gson.annotations.SerializedName

data class TemperatureDto(
    @SerializedName("Minimum")
    val temperatureMinimun : UnitTempDto,
    @SerializedName("Maximum")
    val temperatureMaximum: UnitTempDto
)
