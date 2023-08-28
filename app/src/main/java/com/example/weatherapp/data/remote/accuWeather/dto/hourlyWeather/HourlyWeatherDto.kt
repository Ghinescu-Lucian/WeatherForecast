package com.example.weatherapp.data.remote.accuWeather.dto.hourlyWeather

import com.example.weatherapp.data.remote.accuWeather.dto.PropertyDto
import com.google.gson.annotations.SerializedName

data class HourlyWeatherDto(
    @SerializedName("DateTime")
    val time: String,
    @SerializedName("WeatherIcon")
    val type: Int,
    @SerializedName("IconPhrase")
    val description: String,
    @SerializedName("Temperature")
    val temperature: PropertyDto,
    @SerializedName("Wind")
    val wind: WindHourlyDto,
    @SerializedName("RelativeHumidity")
    val humidity: Double,
    @SerializedName("IsDaylight")
    val isDay: Boolean

)
