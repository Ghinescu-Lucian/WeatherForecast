package com.example.weatherapp.data.remote.accuWeather

import com.example.weatherapp.data.remote.accuWeather.dto.currentWeather.CurrentWeatherDto
import com.example.weatherapp.data.remote.accuWeather.dto.dailyWeather.DailyWeatherDto
import com.example.weatherapp.data.remote.accuWeather.dto.hourlyWeather.HourlyWeatherDto
import com.example.weatherapp.data.remote.accuWeather.dto.locationKey.LocationKeyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val api_key = "Rv1IGtx7Psyf8ma8hSSGneGMTU2rsisq"

interface AccuWeatherApi {

//    locations/v1/cities/geoposition/search
    @GET("locations/v1/cities/geoposition/search")
   suspend fun getLocationKey(
        @Query("q") lat_long: String,
        @Query("details") details: Boolean,
        @Query("apikey") apiKey:String = api_key
   ): LocationKeyDto

   @GET("forecasts/v1/hourly/1hour/{locationKey}")
   suspend fun getOneHourForecast(
       @Path("locationKey") locationKey: String,
       @Query("details") details: Boolean = true,
       @Query("metric") metricUnits: Boolean = true,
       @Query("apikey") apikey: String = api_key
   ): List<CurrentWeatherDto>

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getCurrentForecast(
        @Path("locationKey") locationKey: String,
        @Query("details") details: Boolean = true,
        @Query("apikey") apikey: String = api_key
    ): List<CurrentWeatherDto>

    @GET("forecasts/v1/hourly/12hour/{locationKey}")
    suspend fun getHourlyForecasts(
        @Path("locationKey") locationKey: String ,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean= true,
        @Query("apikey") apikey: String = api_key
    ):List<HourlyWeatherDto>

    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun getDailyForecasts(
        @Path("locationKey") locationKey: String,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean= true,
        @Query("apikey") apikey: String = api_key
    ):DailyWeatherDto
}