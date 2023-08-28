package com.example.weatherapp.data.remote.accuWeather.repository

import android.util.Log
import com.example.weatherapp.data.remote.accuWeather.AccuWeatherApi
import com.example.weatherapp.data.remote.accuWeather.mappers.toWeatherData
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject
import javax.inject.Named

class AccuWeatherRepositoryImpl @Inject constructor (private val api: AccuWeatherApi, ): WeatherRepository {

//    private var locationKey: String = ""
    private var locationKey: String = "278159"
    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
          if(!locationKey.isEmpty()){
              val r = api.getCurrentForecast(locationKey = locationKey)
              Log.d("AccuWeather", r.toString())
              val d = r[0].toWeatherData()
              val result = WeatherInfo(
                  weatherDataPerDay = listOf(
                      WeatherDataPerDay(day = 1,
                          listOf()
                      )
                  ),
                  currentWeatherData = d
              )
              return Result.success(result)

          }
            return Result.failure(Exception("[AccuWeather] Error on getting current forecast"))
    }

  suspend fun getLocationKey(lat: Double, long: Double): Result<String>{
        val s = "$lat,$long"
        var result = api.getLocationKey(s,true)
        locationKey = result.locationKey
        return Result.success(result.toString())
//        else return Result.failure(Exception("[AccuWeather] Error on getting location key"))
    }
}