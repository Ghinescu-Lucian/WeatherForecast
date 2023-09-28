package com.example.weatherapp.data.remote.accuWeather.mappers

import android.util.Log
import com.example.weatherapp.data.remote.accuWeather.dto.currentWeather.CurrentWeatherDto
import com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather.DailyDto
import com.example.weatherapp.data.remote.accuWeather.dto.hourlyWeather.HourlyWeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AccuWeatherMapper {
}

fun CurrentWeatherDto.toWeatherData() : WeatherData{
    return WeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME).toString(),
        temperature = temperature.unit.value,
        pressure = pressure.unit.value,
        windSpeed = wind.speed.unit.value,
        humidity = humidity,
        weatherType = WeatherType.fromAccuWeather(type)
    )
}

fun HourlyWeatherDto.toWeatherData(): WeatherData{

    return WeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME).toString(),
        temperature = temperature.value,
        pressure = 0.0,
        windSpeed = wind.speed.value,
        humidity = humidity,
        description = description,
        weatherType = WeatherType.fromAccuWeather(type),
        isDay = isDay
    )
}

fun DailyDto.toWeatherDataPerDay():WeatherDataPerDay{
    Log.d("Ceva", this.toString())
    return WeatherDataPerDay(
        day = 0,
        minTemperature = temperature.temperatureMinimun.value,
        maxTemperature = temperature.temperatureMaximum.value,
        sunRise = sun.rise,
        sunSet = sun.set,
        moonRise = moon.rise ?: "",
        moonSet = moon.set ?: "",
        weatherTypeDay = WeatherType.fromAccuWeather(day.weatherCode),
        weatherTypeNight = WeatherType.fromAccuWeather(night.weatherCode),
        forecasts = listOf()
    )
}