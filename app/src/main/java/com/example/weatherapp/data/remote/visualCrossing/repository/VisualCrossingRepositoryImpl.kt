package com.example.weatherapp.data.remote.visualCrossing.repository

import android.util.Log
import com.example.weatherapp.data.remote.visualCrossing.VisualCrossingApi
import com.example.weatherapp.data.remote.visualCrossing.mappers.toWeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import javax.inject.Inject

class VisualCrossingRepositoryImpl @Inject constructor (val api: VisualCrossingApi) : WeatherRepository {
    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo> {


        val hourly = getHourlyWeatherData(lat, long)
        var data =  WeatherDataPerDay(
                day =0,
                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 0.0,
                        pressure = 0.0,
                        humidity = 0.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 0.0
                    )
            )
        )
        hourly.onSuccess {
            data = it.weatherDataPerDay[0]
            Log.d("VisualCrossing",data.toString())
        }


        return try{
            val r = api.getWeatherDataCurrent(latitude = lat, longitude = long)
           // Log.d("VisualCrossing2", r.toString())
            var aju = r.toWeatherInfo()
            var aju2 = WeatherInfo(
                currentWeatherData = aju.currentWeatherData,
                weatherDataPerDay = listOf(data)
            )
            Log.d("VisualCrossing2", aju2.toString())

            Result.success(
              aju2
            )
        }catch(e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getHourlyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
       return  try{
           val r = api.getWeatherDataHourly(lat, long)

           return Result.success(
               r.toWeatherInfo()
           )
       } catch (e: Exception){
           e.printStackTrace()
           Result.failure(e)
       }
    }
    override suspend fun getDailyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {

     //   Log.d("[VisualCrossing] Daily",api.getWeatherDataDaily(lat,long).toString())

        return try{
            Result.success(
                api.getWeatherDataDaily(lat, long).toWeatherInfo()
                    )
        } catch(e: Exception){
            e.printStackTrace()
             Result.failure(e)
        }
    }
}