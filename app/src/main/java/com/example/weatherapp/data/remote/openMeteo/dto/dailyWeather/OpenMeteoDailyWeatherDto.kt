package com.example.weatherapp.data.remote.openMeteo.dto.dailyWeather

import com.squareup.moshi.Json

data class OpenMeteoDailyWeatherDto(
    @field: Json(name ="time")
    val times: List<String>,
    @field: Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field: Json(name = "temperature_2m_max")
    val temperaturesMax: List<Double>,
    @field: Json(name = "temperature_2m_min")
    val temperaturesMin: List<Double>,
    @field:Json(name = "sunrise")
    val sunrises: List<String>,
    @field: Json(name = "sunset")
    val sunsets:  List<String>
)
