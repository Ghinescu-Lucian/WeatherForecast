package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo>

}