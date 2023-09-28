package com.example.weatherapp.data.remote.openMeteo

import com.example.weatherapp.data.remote.openMeteo.dto.curentHourlyWeather.WeatherDto
import com.example.weatherapp.data.remote.openMeteo.dto.dailyWeather.OpenMeteoDailyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat : Double,
        @Query("longitude") long: Double,
        @Query("timezone") timezone: String,
    ): WeatherDto

    @GET("v1/forecast?hourly=&daily=weathercode,temperature_2m_max,temperature_2m_min,sunrise,sunset")
    suspend fun getDailyWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("timezone") timezone: String,
    ): OpenMeteoDailyDto
}