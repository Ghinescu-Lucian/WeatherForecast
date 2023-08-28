package com.example.weatherapp.data.remote.accuWeather.repository

import android.util.Log
import com.example.weatherapp.data.remote.accuWeather.AccuWeatherApi
import com.example.weatherapp.data.remote.accuWeather.mappers.toWeatherData
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class AccuWeatherRepositoryImpl @Inject constructor (private val api: AccuWeatherApi, ): WeatherRepository {

//    private var locationKey: String = ""
    private var locationKey: String = "278159"
    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
          if(!locationKey.isEmpty()){
              val r = api.getCurrentForecast(locationKey = locationKey)
              Log.d("AccuWeather", r.toString())
              val hours = getHourlyWeatherData(lat,long)
              var hourlyForecasts : List<WeatherDataPerDay> = listOf(WeatherDataPerDay(
                  day = 0,
                  listOf()
              ))
              hours.onSuccess {
                  hourlyForecasts = it.weatherDataPerDay
              }
              val d = r[0].toWeatherData()
              val result = WeatherInfo(

                  weatherDataPerDay = hourlyForecasts,
                  currentWeatherData = d
              )
              return Result.success(result)

          }
            return Result.failure(Exception("[AccuWeather] Error on getting current forecast"))
    }

    suspend fun getHourlyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
        if( !locationKey.isEmpty() )
        {
            val r = api.getHourlyForecasts(locationKey = locationKey)
            Log.d("AccuWeather Hourly", r.toString())
            val list = mutableListOf<WeatherData>()

            for (i in 0..11){
                list.add(
                    r[i].toWeatherData()
                )
            }

            return Result.success(
                WeatherInfo(
                    currentWeatherData = list[0],
                    weatherDataPerDay = listOf(WeatherDataPerDay(
                        day = 0,
                        forecasts = list
                    ))
                )
            )

        }

        return Result.failure(Exception("[AccuWeather] Hourly forecasts error (empty location key)."))
    }

  suspend fun getLocationKey(lat: Double, long: Double): Result<String>{
        val s = "$lat,$long"
        var result = api.getLocationKey(s,true)
        locationKey = result.locationKey
        return Result.success(result.toString())
//        else return Result.failure(Exception("[AccuWeather] Error on getting location key"))
    }
}