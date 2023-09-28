package com.example.weatherapp.data.remote.accuWeather.repository

import android.util.Log
import com.example.weatherapp.data.remote.accuWeather.AccuWeatherApi
import com.example.weatherapp.data.remote.accuWeather.dto.locationKey.LocationKeyDto
import com.example.weatherapp.data.remote.accuWeather.mappers.toWeatherData
import com.example.weatherapp.data.remote.accuWeather.mappers.toWeatherDataPerDay
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class AccuWeatherRepositoryImpl @Inject constructor(private val api: AccuWeatherApi) :
    WeatherRepository {

//        private var locationKey: String = "290867"
    private var locationKey: String = ""
    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<WeatherInfo> {

        if (locationKey.isEmpty()) {
            val r = getLocationKey(lat, long).onSuccess {
                locationKey = it
            }
            Log.d("LocationKey","Ceva " + r.toString() + "\n"+locationKey)
            if (r.isFailure)
                return Result.failure(Exception("[AccuWeather] Error on location key."))
        }

        val r = api.getCurrentForecast(locationKey = locationKey)
        Log.d("AccuWeather", r.toString())
        val hours = getHourlyWeatherData(lat, long)
        var hourlyForecasts: List<WeatherDataPerDay> = listOf(
            WeatherDataPerDay(
                day = 0,
                forecasts = listOf()
            )
        )
        hours.onSuccess {
            hourlyForecasts = it.weatherDataPerDays
        }
        val d = r[0].toWeatherData()
        val result = WeatherInfo(

            weatherDataPerDays = hourlyForecasts,
            currentWeatherData = d
        )
        return Result.success(result)
//        return Result.success(WeatherInfo(
//            weatherDataPerDays = listOf(),
//            currentWeatherData = null
//        ))


    }

    override suspend fun getHourlyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
        if (locationKey.isEmpty()) {
            val res = getLocationKey(lat, long).onSuccess {
                locationKey = it
            }
            if (res.isFailure)
                return Result.failure(Exception("[AccuWeather] Error on location key."))

        }
        val r = api.getHourlyForecasts(locationKey = locationKey)
        Log.d("AccuWeather Hourly", r.toString())
        val list = mutableListOf<WeatherData>()

        for (i in 0..11) {
            list.add(
                r[i].toWeatherData()
            )
        }

        return Result.success(
            WeatherInfo(
                currentWeatherData = list[0],
                weatherDataPerDays = listOf(
                    WeatherDataPerDay(
                        day = 0,
                        forecasts = list
                    )
                )
            )
        )



       // return Result.failure(Exception("[AccuWeather] Hourly forecasts error (empty location key)."))
    }

    override suspend fun getDailyWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
        if (locationKey.isEmpty()) {
            val r = getLocationKey(lat, long).onSuccess {
                locationKey = it
            }
            if (r.isFailure)
                return Result.failure(Exception("[AccuWeather] Error on location key."))
        }
        val result = api.getDailyForecasts(locationKey = locationKey)
        Log.d("Daily", result.toString())
       // val list = mutableListOf<WeatherDataPerDay>()
        result.dailyForecasts.forEachIndexed { index, dailyDto ->

            var aju = dailyDto.toWeatherDataPerDay()
//                index, dailyWeatherDto ->
//            var aju = dailyWeatherDto.toWeatherDataPerDay()
//        }
//            aju.day = index
//            list.add(aju)
        }
        return Result.success(
            WeatherInfo(
                currentWeatherData = null,
                weatherDataPerDays = listOf()
            )
        )

    }

    suspend fun getLocationKey(lat: Double, long: Double): Result<String> {
        val s = "$lat,$long"
        var result=  LocationKeyDto("", "")
        try {
             result = api.getLocationKey(s, true)
//            Log.d("LocationKey Result:", result.locationKey)
        } catch( e: Exception){
            return Result.failure(Exception("[Accuweather] Location key"))
        }
        locationKey = result.locationKey
        Log.d("LocationKey", result.locationKey)
        return Result.success(result.locationKey)
    }
}