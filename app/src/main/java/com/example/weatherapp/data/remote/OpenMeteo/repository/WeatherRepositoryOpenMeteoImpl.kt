package com.example.weatherapp.data.remote.OpenMeteo.repository

import com.example.weatherapp.data.remote.OpenMeteo.OpenMeteoApi
import com.example.weatherapp.data.remote.OpenMeteo.mappers.toWeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

//import javax.inject.Inject

class WeatherRepositoryOpenMeteoImpl @Inject constructor(
        private val api: OpenMeteoApi
) : WeatherRepository {
        override suspend fun getWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
                return try{
                        val r = api.getWeatherData(lat = lat, long = long)
//                        Log.d("Retrofit",r.weatherData.toString())
                        Result.success(
                                 api.getWeatherData(
                                        lat = lat,
                                        long = long
                                ).toWeatherInfo()

                        )

                }catch(e: Exception){
                        e.printStackTrace()
                        Result.failure(e)
                }
        }

}