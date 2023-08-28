package com.example.weatherapp.data.remote.visualCrossing

import com.example.weatherapp.data.remote.visualCrossing.dto.VisualCrossingDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VisualCrossingApi {

    @GET("{latitude},{longitude}/?unitGroup=metric&include=hours&key=GAJN9RELWJUAMQJUQZEYTUP5R&contentType=json")
    suspend fun getWeatherDataHourly(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): VisualCrossingDto
}