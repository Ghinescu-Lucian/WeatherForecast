package com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather

import com.google.gson.annotations.SerializedName

data class DailyDto(
    @SerializedName("Date")
    val date : String,
    @SerializedName("Sun")
    val sun: CelestialDto,
    @SerializedName("Moon")
    val moon: CelestialDto,
    @SerializedName("Temperature")
    val temperature: TemperatureDto,
    @SerializedName("Day")
    val day: DayDto,
    @SerializedName("Night")
    val night: DayDto
)
