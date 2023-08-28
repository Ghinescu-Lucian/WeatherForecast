package com.example.weatherapp.data.remote.accuWeather.dto.currentWeather

import com.google.gson.annotations.SerializedName

data class WindCurrentDto(
    @SerializedName("Speed")
    val speed: UnitDto
)
