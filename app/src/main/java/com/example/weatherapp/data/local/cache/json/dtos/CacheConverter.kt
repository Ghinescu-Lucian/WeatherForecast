package com.example.weatherapp.data.local.cache.json.dtos

import com.example.weatherapp.data.local.cache.Cache
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.google.gson.Gson

class CacheConverter{

    val gson = Gson()

    fun convertToJson(weatherInfo: WeatherInfo): String{
        var result = ""

        result = gson.toJson(weatherInfo)

        return result
    }

    fun convertFromJson(weatherInfoJson: String): WeatherInfoDto?{
        var result : WeatherInfoDto? = null
        if(weatherInfoJson.isNotEmpty())
            result = gson.fromJson(weatherInfoJson, WeatherInfoDto::class.java)
        return result
    }

    fun convertToWeatherInfo(cache: Cache) : WeatherInfo
    {

        val data = convertFromJson(cache.data)
        val typeConverter = ConverterWeatherType()



        val weatherDataPerDays = mutableListOf<WeatherDataPerDay>()
        val currentWeatherData = convertWeatherDataDtoToOriginal(data!!.currentWeatherData)
        data.weatherDataPerDays.forEach{ it ->
            val forecasts = mutableListOf<WeatherData>()
            it.forecasts.forEach{ item ->
                forecasts.add(convertWeatherDataDtoToOriginal(item))
            }
           weatherDataPerDays.add(
               WeatherDataPerDay(
                day = it.day,
                minTemperature = it.minTemperature,
                maxTemperature = it.maxTemperature,
                sunRise = it.sunRise,
                sunSet = it.sunSet,
                moonRise = it.moonRise,
                moonSet = it.moonSet,
                weatherTypeDay = typeConverter.convertDtoToOriginal(it.weatherTypeDay),
                weatherTypeNight = typeConverter.convertDtoToOriginal(it.weatherTypeNight),
                forecasts = forecasts
            )
           )
        }

        val wInfo = WeatherInfo(
            currentWeatherData = currentWeatherData,
            partialResult = data.partialResult,
            weatherDataPerDays = weatherDataPerDays

        )

        return wInfo

    }

    fun convertWeatherDataDtoToOriginal(data: WeatherDataJsonDto): WeatherData{
        val typeConverter = ConverterWeatherType()

        return WeatherData(
            time = data!!.time,
            humidity = data.humidity,
            pressure = data.pressure,
            temperature = data.temperature,
            windSpeed = data.windSpeed,
            weatherType = typeConverter.convertDtoToOriginal(data.weatherType),
        )


    }




}