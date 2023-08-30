package com.example.weatherapp.domain.weather.Interactors

import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
// @Inject constructor
class AverageCalculator (

    val weatherRepositories : Set<WeatherRepository>

){
//  : Result<WeatherInfo>
    suspend fun calculateAverage(lat: Double, long: Double)= coroutineScope{



       val allDeferred= weatherRepositories.map{
            async {
                it.getCurrentWeatherData(lat, long)
            }
        }.toTypedArray()
        val allResults = awaitAll(*allDeferred)

        val isPartialResult = allResults.any{
            it.isFailure
        }

    }


}