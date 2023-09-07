package com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather

import com.google.gson.annotations.SerializedName

data class DailyWeatherDto(
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyDto>
)
