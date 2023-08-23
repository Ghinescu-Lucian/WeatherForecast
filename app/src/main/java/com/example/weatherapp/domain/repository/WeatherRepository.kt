package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): Result<WeatherInfo>

}