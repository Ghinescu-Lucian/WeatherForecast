package com.example.weatherapp.data.remote.mappers

import androidx.compose.ui.text.rememberTextMeasurer
import com.example.weatherapp.data.remote.WeatherDataDto
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



// mai am de lucru aici

fun WeatherDataDto.toWeatherDataPerDay() : List<WeatherDataPerDay>{
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        WeatherDataPerDay(
            day = index,
            listOf(
                WeatherData(
            time  = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius =  temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
            )
            )
        )
    }

}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataPerDay = weatherData.toWeatherDataPerDay()
    val now = LocalDateTime.now()

    val index = if(now.minute < 30) now.hour else now.hour+1

    val currentWeatherData = weatherDataPerDay[0].forecasts[index]

//    val currentWeatherData = weatherDataPerDay[0]?.find{
//        val hour = if(now.minute <30) now.hour else now.hour+1
//        it.time.hour == hour
//    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataPerDay,
        currentWeatherData =  currentWeatherData
    )

}