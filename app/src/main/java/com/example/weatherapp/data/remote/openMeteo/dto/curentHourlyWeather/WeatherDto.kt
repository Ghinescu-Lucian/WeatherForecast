package com.example.weatherapp.data.remote.openMeteo.dto.curentHourlyWeather

import com.squareup.moshi.Json

data class WeatherDto(

    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
