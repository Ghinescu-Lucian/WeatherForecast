package com.example.weatherapp.data.remote.openMeteo.repository

import com.example.weatherapp.data.remote.openMeteo.OpenMeteoApi
import com.example.weatherapp.data.remote.openMeteo.mappers.toWeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import java.util.TimeZone
import javax.inject.Inject

//import javax.inject.Inject

class WeatherRepositoryOpenMeteoImpl @Inject constructor(
        private val api: OpenMeteoApi
) : WeatherRepository {
        override suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
            val timezone = TimeZone.getDefault()
            return try{
                        //val r = api.getWeatherData(lat = lat, long = long)
//                        Log.d("Location","$lat + $long")
                        Result.success(
                                 api.getWeatherData(
                                        lat = lat,
                                        long = long,
                                        timezone = timezone.id
                                ).toWeatherInfo()

                        )

                }catch(e: Exception){
                        e.printStackTrace()
                        Result.failure(e)
                }
        }

    override suspend fun getHourlyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
        return try{
            //val r = api.getWeatherData(lat = lat, long = long)
//            Log.d("Location","$lat + $long")
            Result.success(
                api.getWeatherData(
                    lat = lat,
                    long = long,
                    timezone = TimeZone.getDefault().id
                ).toWeatherInfo()

            )

        }catch(e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getDailyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
        return try{

            Result.success(
                api.getDailyWeatherData(
                    lat = lat,
                    long = long,
                    timezone = TimeZone.getDefault().id
                ).toWeatherInfo()

            )

        }catch(e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

}