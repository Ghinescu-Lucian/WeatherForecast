package com.example.weatherapp.data.remote.accuWeather

import com.example.weatherapp.data.remote.accuWeather.dto.CurrentWeatherDto
import com.example.weatherapp.data.remote.accuWeather.dto.LocationKeyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuWeatherApi {

//    locations/v1/cities/geoposition/search
    @GET("locations/v1/cities/geoposition/search")
   suspend fun getLocationKey(
        @Query("q") lat_long: String,
        @Query("details") details: Boolean,
        @Query("apikey") apiKey:String = "Rv1IGtx7Psyf8ma8hSSGneGMTU2rsisq"
    ): LocationKeyDto

   @GET("forecasts/v1/hourly/1hour/{locationKey}")
   suspend fun getCurrentForecast(
       @Path("locationKey") locationKey: String,
       @Query("details") details: Boolean = true,
       @Query("metric") metricUnits: Boolean = true,
       @Query("apikey") apikey: String = "Rv1IGtx7Psyf8ma8hSSGneGMTU2rsisq"

   ): List<CurrentWeatherDto>
}