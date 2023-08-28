package com.example.weatherapp.data.remote.visualCrossing.repository

import android.util.Log
import com.example.weatherapp.data.remote.visualCrossing.VisualCrossingApi
import com.example.weatherapp.data.remote.visualCrossing.mappers.toWeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class VisualCrossingRepositoryImpl @Inject constructor (val api: VisualCrossingApi) : WeatherRepository {
    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
//
        return try{
           // val r = api.getWeatherDataHourly(latitude = lat, longitude = long)
            Log.d("VisualCrossing", api.getWeatherDataHourly(
                latitude = lat,
                longitude = long
            ).toString())
            Result.success(
                api.getWeatherDataHourly(
                    latitude = lat,
                    longitude = long
                ).toWeatherInfo()

            )

        }catch(e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }
}