package com.example.weatherapp.data.remote.visualCrossing

import com.example.weatherapp.data.remote.visualCrossing.dto.DailyDto.VisualCrossingDailyDto
import com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto.VisualCrossingDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VisualCrossingApi {

    // current weather + 24 hours weather
    @GET("{latitude},{longitude}/?unitGroup=metric&include=hours&key=GAJN9RELWJUAMQJUQZEYTUP5R&contentType=json")
    suspend fun getWeatherDataHourly(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): VisualCrossingDto

// get 15 days weather, without moon rise/set
    @GET("{latitude},{longitude}/next7days?unitGroup=metric&include=days&key=GAJN9RELWJUAMQJUQZEYTUP5R&contentType=json")
    suspend fun getWeatherDataDaily(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): VisualCrossingDailyDto
}