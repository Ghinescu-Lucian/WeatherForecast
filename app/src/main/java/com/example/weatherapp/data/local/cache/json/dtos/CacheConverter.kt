package com.example.weatherapp.data.local.cache.json.dtos

import com.example.weatherapp.domain.weather.WeatherInfo
import com.google.gson.Gson

class CacheConverter{

    val gson = Gson()

    fun ConvertToJson(weatherInfo: WeatherInfo): String{
        var result = ""

        result = gson.toJson(weatherInfo)

        return result
    }

    fun ConvertFromJson(weatherInfoJson: String): WeatherInfoDto?{
        var result : WeatherInfoDto? = null
        if(weatherInfoJson.isNotEmpty())
            result = gson.fromJson(weatherInfoJson, WeatherInfoDto::class.java)
        return result
    }


}