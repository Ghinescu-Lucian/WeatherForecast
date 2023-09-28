package com.example.weatherapp.data.local.cache.json.dtos

import com.example.weatherapp.domain.weather.WeatherType
import com.google.gson.annotations.SerializedName

data class WeatherDataPerDayJsonDto (
    @SerializedName("day") var day: Int,
    @SerializedName("minTemperature") val minTemperature: Double ,
    @SerializedName("maxTemperature") val maxTemperature: Double ,
    @SerializedName("sunRise") val sunRise: String,
    @SerializedName("sunSet") val sunSet: String ,
    @SerializedName("moonRise") val moonRise: String ,
    @SerializedName("moonSet") val moonSet: String ,
    @SerializedName("weatherTypeDay") val weatherTypeDay: WeatherTypeDto = ConverterWeatherType().convertToDto(WeatherType.ClearSky),
    @SerializedName("weatherTypeNight") val weatherTypeNight: WeatherTypeDto = ConverterWeatherType().convertToDto(WeatherType.ClearSky),
    @SerializedName("forecasts") val forecasts:List<WeatherDataJsonDto>
)