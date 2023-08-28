package com.example.weatherapp.data.remote.accuWeather.mappers

import com.example.weatherapp.data.remote.accuWeather.dto.CurrentWeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AccuWeatherMapper {
}

fun CurrentWeatherDto.toWeatherData() : WeatherData{
    return WeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
        temperatureCelsius = temperature.value,
        pressure = 0.0,
        windSpeed = wind.windSpeed.value,
        humidity = humidity,
        weatherType = WeatherType.fromAccuWeather(type)
    )
}