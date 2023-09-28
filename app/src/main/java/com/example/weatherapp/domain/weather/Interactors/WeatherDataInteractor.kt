package com.example.weatherapp.domain.weather.Interactors

//import androidx.compose.ui.tooling.data.EmptyGroup.location
import android.content.Context
import android.location.Location
import android.util.Log
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

interface WeatherDataInteractor {
    suspend fun getCityName(context: Context): Result<String>
    suspend fun getCoordinates(): Result<Location?>
    suspend fun getWeatherData(location: Location?, refresh: Boolean): Result<WeatherData> //
    suspend fun getWeatherDataPerDay(days: Int = 12, location : Location?, refresh:Boolean ): Result<List<WeatherDataPerDay>>

    suspend fun getWeatherDataPerHour():Result<WeatherDataPerDay>
}

private object weatherState{
     var currentWeather : Result<WeatherData> = failure(Exception("Initialize"))
     var days : Result<List<WeatherDataPerDay>> = failure(Exception("Initialize"))
}

class WeatherDataInteractorImpl @Inject constructor (
    val locationTracker: LocationTracker,
    // sa am o lista de repository
    val averageCalculator: AverageCalculator,
//   @Named("OpenMeteo") val  weatherRepository: WeatherRepository,

    ) : WeatherDataInteractor {


    override suspend fun getCoordinates(): Result<Location?> {
        return   locationTracker.getCurrentLocation()
    }
   override suspend fun getCityName(context: Context): Result<String>{
        val location = locationTracker.getCurrentLocation()
        val result: Result<String> = failure(Exception("Error on interactor( time-out )"))
        val citySearch = CitySearch()

        location.onSuccess {
            it?.latitude ?: return failure(Exception("Error on location provider."))
            var city = citySearch.getCityName(context, it.latitude, it.longitude)
            if(city.isEmpty())
                city = "Unknown"
            return success(city)
        }

       return result

    }
    override suspend fun getWeatherData(location: Location?, refresh: Boolean): Result<WeatherData> {

        Log.d("Weather Interactor", location.toString())
        if(weatherState.currentWeather.isFailure || refresh) {

            val result: Result<WeatherData>

            location?.latitude ?: return failure(Exception("Error on location provider."))

                //   Log.d("[Interactor Weather] Location", it.toString())
                val r = averageCalculator.calculateAverage(location.latitude, location.longitude)
//                val r2 = weatherRepository.getDailyWeatherData(it.latitude, it.longitude)
               Log.d("CurrentWeather", r.toString())

                    result = success(r.currentWeatherData) as Result<WeatherData>
                    weatherState.currentWeather = result
                    weatherState.days = success(r.weatherDataPerDays)




            return result
        } else {
            return weatherState.currentWeather
        }


    }

    override suspend fun getWeatherDataPerDay(days: Int, location: Location?, refresh: Boolean): Result<List<WeatherDataPerDay>> {
        if(weatherState.days.isFailure || refresh ){

        var result: Result<List<WeatherDataPerDay>> = failure(Exception("Error on interactor"))


            location?.latitude ?: return failure(Exception("Error on location provider."))
            val r = averageCalculator.calculateAverage(location.latitude, location.longitude)

                result = Result.success(r.weatherDataPerDays)




        return result
        } else {
            return weatherState.days
        }

    }

    override suspend fun getWeatherDataPerHour(): Result<WeatherDataPerDay> {
        if(weatherState.days.isFailure){
            val location = locationTracker.getCurrentLocation()
            var result: Result<WeatherDataPerDay> = failure(Exception("Error on interactor"))

            location.onSuccess {
                it?.latitude ?: return failure(Exception("Error on location provider."))
                val r = averageCalculator.calculateAverage(it.latitude, it.longitude)
                result = Result.success(r.weatherDataPerDays[0])

            }

            return result
        } else {
            weatherState.days.onSuccess {
                return success( it[0])
            }
            return failure(Exception("Error on getting hourly data"))
        }

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

        return Result.success(currentWeather)
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

                    ),WeatherData(
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
               forecasts =  listOf(
                    WeatherData(
                        time = time.toString(),
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavyHailThunderstorm

                    ),WeatherData(
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