package com.example.weatherapp

import android.app.Application
import android.util.Log
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.data.local.cache.Cache
import com.example.weatherapp.data.local.cache.CacheDB
import com.example.weatherapp.data.local.cache.CacheRepository
import com.example.weatherapp.data.local.cache.json.dtos.CacheConverter
import com.example.weatherapp.data.local.weights.WeightRepository
import com.example.weatherapp.data.local.weights.WeightsDB
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import javax.inject.Inject


@Serializable
data class Person(val name: String, val age: Int)


@HiltAndroidApp
class WeatherApp: Application()
{
    @Inject
    lateinit var db: WeightsDB

    @Inject
    lateinit var repository: WeightRepository

    @Inject
    lateinit var cacheDb: WeightsDB

    @Inject
    lateinit var repositoryCache: CacheRepository





    val weatherInfo = WeatherInfo(
        currentWeatherData = WeatherData(
            time = LocalDateTime.now().toString().toString(),
            temperature = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(45),
            windSpeed = 12.0
        ),
        weatherDataPerDays = listOf(
            WeatherDataPerDay(
                day =0,
                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now().toString().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString().toString(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(1),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(56),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(75),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(45),
                        windSpeed = 12.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =1,
                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(2),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 12.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =2,
                forecasts =  listOf(
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(45),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =3,
                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =4,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now().toString(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    )

                )
            ),
        )
    )




        override fun onCreate() {
        super.onCreate()

//            Log.d("Converter",  weatherInfo.currentWeatherData?.time.toString())
////
             val res = CacheConverter().ConvertToJson(weatherInfo = weatherInfo)
            val cache = Cache(
                id = 0,
                city = "Timisoara",
                partialResult = false,
                data = res
            )


            val citySearch = CitySearch()
            val response = citySearch.retrieveCoordinates("Novigrad",this)
            Log.d("CitySearch", response.toString())

//            val wg = Weights(id = 2, "Munich",1.0, 1.0, 1.0, 48.142286, 11.579616)

            GlobalScope.launch {
//
//                repository.insert(wg)
                repository.allWeights.collect{ it ->
                    Log.d("Database", it.size.toString())
                    it.forEach{itt ->
                        Log.d("Database", itt.toString())
                    }

//                    repositoryCache.insert(cache)

                    repositoryCache.allCaches.collectLatest {
                        Log.d("Cache: ", it[0].toString())
                    }


                }





            }



    }
}