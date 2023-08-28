package com.example.weatherapp.data.remote.openMeteo

import com.example.weatherapp.data.remote.openMeteo.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat : Double,
        @Query("longitude") long: Double
    ): WeatherDto

}