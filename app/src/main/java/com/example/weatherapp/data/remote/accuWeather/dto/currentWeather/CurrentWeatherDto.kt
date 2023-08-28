package com.example.weatherapp.data.remote.accuWeather.dto.currentWeather

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("LocalObservationDateTime")
    val time: String,
    @SerializedName("WeatherIcon")
    val type: Int,
    @SerializedName("Weathertext")
    val description: String,
    @SerializedName("Temperature")
    val temperature: UnitDto,
    @SerializedName("Wind")
    val wind: WindCurrentDto,
    @SerializedName("RelativeHumidity")
    val humidity: Double,
    @SerializedName("Pressure")
    val pressure: UnitDto
)
