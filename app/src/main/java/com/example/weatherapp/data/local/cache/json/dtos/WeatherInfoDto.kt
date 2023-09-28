package com.example.weatherapp.data.local.cache.json.dtos

import com.google.gson.annotations.SerializedName

data class WeatherInfoDto (
    @SerializedName("partialResult") val partialResult: Boolean,
    @SerializedName("currentWeatherData") val currentWeatherData: WeatherDataJsonDto,
    @SerializedName("weatherDataPerDays") val weatherDataPerDays: List<WeatherDataPerDayJsonDto>
)