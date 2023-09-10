package com.example.weatherapp.domain.weather.Interactors

//import androidx.compose.ui.tooling.data.EmptyGroup.location
import android.util.Log
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

interface WeatherDataInteractor {
    suspend fun getWeatherData(): Result<WeatherData> //
    suspend fun getWeatherDataPerDay(days: Int = 12): Result<List<WeatherDataPerDay>>

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

    override suspend fun getWeatherData(): Result<WeatherData> {

        if(weatherState.currentWeather.isFailure) {
            val location = locationTracker.getCurrentLocation()
            var result: Result<WeatherData> = failure(Exception("Error on interactor( time-out )"))

            location.onSuccess {
                val l = it?.latitude
                if(l == null) return failure(Exception("Error on location provider."))
             //   Log.d("[Interactor Weather] Location", it.toString())
                val r = averageCalculator.calculateAverage(it.latitude, it.longitude)
//                val r2 = weatherRepository.getDailyWeatherData(it.latitude, it.longitude)
               Log.d("CurrentWeather", r.toString())

                    result = Result.success(r.currentWeatherData) as Result<WeatherData>
                    weatherState.currentWeather = result
                    weatherState.days = Result.success(r.weatherDataPerDays)


            }

            return result
        } else {
            return weatherState.currentWeather
        }


    }

    override suspend fun getWeatherDataPerDay(days: Int): Result<List<WeatherDataPerDay>> {
        if(weatherState.days.isFailure){
        val location = locationTracker.getCurrentLocation()
        var result: Result<List<WeatherDataPerDay>> = failure(Exception("Error on interactor"))

        location.onSuccess {
            val l = it?.latitude
            if(l == null) return failure(Exception("Error on location provider."))
            val r = averageCalculator.calculateAverage(it.latitude, it.longitude)

                result = Result.success(r.weatherDataPerDays)


        }

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
                val l = it?.latitude
                if(l == null) return failure(Exception("Error on location provider."))
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
            time = time,
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
                        time = time,
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.ClearSky

                    ),WeatherData(
                        time = time,
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.DenseDrizzle
                    ), WeatherData(
                        time = time,
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
                        time = time,
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavyHailThunderstorm

                    ),WeatherData(
                        time = time,
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavySnowFall
                    ), WeatherData(
                        time = time,
                        temperature = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.SlightHailThunderstorm


                    )
                )
            )
        )

        return Result.success(weatherDay)



    }
}