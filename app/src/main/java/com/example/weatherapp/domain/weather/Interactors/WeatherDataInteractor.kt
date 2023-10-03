package com.example.weatherapp.domain.weather.Interactors

import android.content.Context
import android.location.Location
import android.util.Log
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.data.local.cache.Cache
import com.example.weatherapp.data.local.cache.CacheRepository
import com.example.weatherapp.data.local.cache.json.dtos.CacheConverter
import com.example.weatherapp.data.local.weights.WeightRepository
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.viewModels.Point
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

interface WeatherDataInteractor {
    suspend fun getCityName(context: Context): Result<String>
    suspend fun getCoordinates(): Result<Location?>
    suspend fun getWeatherData(location: Location?, refresh: Boolean, city: String, offline: Boolean): Result<WeatherInfo> //
    suspend fun getWeatherDataPerDay(days: Int = 12, location : Location?, refresh:Boolean, city: String, offline: Boolean ): Result<List<WeatherDataPerDay>>

    suspend fun getWeatherDataPerHour():Result<WeatherDataPerDay>

    suspend fun getWeights(location:Location?, point: Point): Weights

    fun offline(offline: Boolean)
}
//
// object WeatherStateInt{
//     var currentWeather : Result<WeatherData> = failure(Exception("Initialize"))
//     var days : Result<List<WeatherDataPerDay>> = failure(Exception("Initialize"))
//     var offline : Boolean = false
//}

class WeatherDataInteractorImpl @Inject constructor (
    val locationTracker: LocationTracker,
    // sa am o lista de repository
    val averageCalculator: AverageCalculator,
//   @Named("OpenMeteo") val  weatherRepository: WeatherRepository,

    val cacheRepository: CacheRepository,
    val weightRepository: WeightRepository

    ) : WeatherDataInteractor {

//   val state = WeatherStateInt

    override suspend fun getCoordinates(): Result<Location?> {
        return locationTracker.getCurrentLocation()
    }

    override suspend fun getCityName(context: Context): Result<String> {
        val location = locationTracker.getCurrentLocation()
        val result: Result<String> = failure(Exception("Error on interactor( time-out )"))
        val citySearch = CitySearch()

        location.onSuccess {
            it?.latitude ?: return failure(Exception("Error on location provider."))
            var city = citySearch.getCityName(context, it.latitude, it.longitude)
            if (city.isEmpty())
                city = "Unknown"
            return success(city)
        }

        return result

    }

    override suspend fun getWeatherData(
        location: Location?,
        refresh: Boolean,
        city: String,
        offline: Boolean
    ): Result<WeatherInfo> {

//        val cache = cacheRepository.allCaches.collect{
//            Log.d("Cache: ", it.toString())
//        }
        var r = WeatherInfo(listOf(), null)

       Log.d("Cacheul", "am ajuns aici1" + offline+" "+refresh)


        if (refresh || cacheRepository.allCaches.first().isEmpty()) {

            var result: Result<WeatherData> = failure(Exception("Initialize"))

            if (!offline) {
                Log.d("Cacheul1®", "am ajuns aici1" + offline)
                location?.latitude ?: return failure(Exception("Error on location provider."))

                Log.d("Cacheul1", "am ajuns aici1" + offline)
                //   Log.d("[Interactor Weather] Location", it.toString())
                Log.d("Interactor: Weights", averageCalculator.weights.toString())
                 r = averageCalculator.calculateAverage(location.latitude, location.longitude)
//                val r2 = weatherRepository.getDailyWeatherData(it.latitude, it.longitude)
                Log.d("CurrentWeatherfzx", r.toString() + "cit: " + city)

                cacheRepository.deleteAll()
//                cacheRepository.allCaches.first{
//                    if(it.size > 0) {
//                        cacheRepository.deleteAll()
//                    }
//                    true
//                }

                Log.d("Cacheul", "am ajuns aici")
                cacheRepository.insert(
                    Cache(
                        0,
                        city,
                        false,
                        data = CacheConverter().convertToJson(r)
                    )
                )

                result = success(r.currentWeatherData) as Result<WeatherData>
//                state.currentWeather = result
//                state.days = success(r.weatherDataPerDays)


            } else {
                var cache: Cache
//                cacheRepository.allCaches.first(){
//                    cache = it.last()
//                    true
//                }


            }

            return success(r)

        } else {
//            return state.currentWeather
//            return success(r)
            return failure(Exception("Error interactor"))
        }


    }

    override suspend fun getWeatherDataPerDay(
        days: Int,
        location: Location?,
        refresh: Boolean,
        city: String,
        offline: Boolean
    ): Result<List<WeatherDataPerDay>> {
        if (refresh) {

            val result: Result<List<WeatherDataPerDay>>

            location?.latitude ?: return failure(Exception("Error on location provider."))

            val r = averageCalculator.calculateAverage(location.latitude, location.longitude)

            result = success(r.weatherDataPerDays)

            return result
        } else {
            return failure(java.lang.Exception("Error on get daily weather"))
        }

    }

    override suspend fun getWeatherDataPerHour(): Result<WeatherDataPerDay> {

        val location = locationTracker.getCurrentLocation()
        var result: Result<WeatherDataPerDay> = failure(Exception("Error on interactor"))

        location.onSuccess {
            it?.latitude ?: return failure(Exception("Error on location provider."))
            val r = averageCalculator.calculateAverage(it.latitude, it.longitude)
            result = success(r.weatherDataPerDays[0])

        }

        return result

    }


    override suspend fun getWeights(location: Location?, point: Point): Weights {
        val points: List<Weights> = weightRepository.allWeights()
        var closestPoint = Weights(0, "", 1.0, 1.0, 1.0, 1.0, 1.0)


        var currentPositon = LatLng(0.0, 0.0)
        if (location != null) {
            currentPositon = LatLng(location.latitude, location.longitude)
        }

        closestPoint = DistanceCalculator().getClosestPoint(points, currentPositon)

        val weights =
            listOf(closestPoint.omWeight, closestPoint.vcWeight, closestPoint.accWeight)

        averageCalculator.weights = weights
        point.point = closestPoint
        Log.d("Get Weights", closestPoint.toString())




        return closestPoint

    }


    override fun offline(offline: Boolean) {

    }


    fun getWeatherDataMock(): Result<WeatherData> {

        val time = LocalDateTime.now()
        val currentWeather = WeatherData(
            time = time.toString(),
            temperature = 27.0,
            pressure = 180.0,
            windSpeed = 11.0,
            humidity = 30.0,
            weatherType = WeatherType.ClearSky
        )

        return success(currentWeather)
    }

    fun getWeatherDataPerDayMock(): Result<List<WeatherDataPerDay>> {

        val time = LocalDateTime.now()
        val weatherDay = listOf(
            WeatherDataPerDay(
                day = 1,
                forecasts = listOf(
                    WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.ClearSky

                    ), WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.DenseDrizzle
                    ), WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.DenseFreezingDrizzle


                    )
                )
            ),
            WeatherDataPerDay(
                day = 2,
                forecasts = listOf(
                    WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavyHailThunderstorm

                    ), WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavySnowFall
                    ), WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.SlightHailThunderstorm


                    )
                )
            )
        )

        return success(weatherDay)


    }
}
