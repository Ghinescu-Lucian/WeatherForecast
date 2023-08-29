package com.example.weatherapp.domain.weather.Interactors

//import androidx.compose.ui.tooling.data.EmptyGroup.location
import android.util.Log
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named
import kotlin.Result.Companion.failure

interface WeatherDataInteractor {
    suspend fun getWeatherData(): Result<WeatherData> //
    suspend fun getWeatherDataPerDay(days: Int = 12): Result<List<WeatherDataPerDay>>
}

private object weatherState{
     var currentWeather : Result<WeatherData> = failure(Exception("Initialize"))
     var days : Result<List<WeatherDataPerDay>> = failure(Exception("Initialize"))
}

class WeatherDataInteractorImpl @Inject constructor (
    val locationTracker: LocationTracker,
   @Named("OpenMeteo" ) val  weatherRepository: WeatherRepository
    ) : WeatherDataInteractor {

    override suspend fun getWeatherData(): Result<WeatherData> {
        Log.d("Interactor",weatherRepository::class.java.simpleName.toString().takeLast(50))
        if(weatherState.currentWeather.isFailure) {
            val location = locationTracker.getCurrentLocation()
            var result: Result<WeatherData> = failure(Exception("Error on interactor"))
            location.onSuccess {
                val l = it?.latitude
                if(l == null) return failure(Exception("Error on location provider."))
                val r = weatherRepository.getCurrentWeatherData(it.latitude, it.longitude)

                r.onSuccess { itt ->
                    result = Result.success(itt.currentWeatherData) as Result<WeatherData>
                    weatherState.currentWeather = result
                    Log.d("Interactor",itt.weatherDataPerDay.toString())
                    weatherState.days = Result.success(itt.weatherDataPerDay)
//                    weatherState.days = re
                }
            }

            return result
        } else {
            return weatherState.currentWeather
        }
    }
    override suspend fun getWeatherDataPerDay(days: Int): Result<List<WeatherDataPerDay>> {
        if(weatherState.days.isFailure){
            Log.d("WeatherForecast","AICI")
        val location = locationTracker.getCurrentLocation()
        var result: Result<List<WeatherDataPerDay>> = failure(Exception("Error on interactor"))

        location.onSuccess {
            val l = it?.latitude
            if(l == null) return failure(Exception("Error on location provider."))
            val r = weatherRepository.getCurrentWeatherData(it.latitude, it.longitude)
            r.onSuccess {  itt ->
                result = Result.success(itt.weatherDataPerDay)
                Log.d("WeatherForecast",itt.weatherDataPerDay.get(0).toString())

            }
        }

        return result
        } else {
            return weatherState.days
        }

    }

    fun getWeatherDataMock(): Result<WeatherData> {

        val time = LocalDateTime.now()
        val currentWeather = WeatherData(
            time = time,
            temperatureCelsius = 27.0,
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
                listOf(
                    WeatherData(
                        time = time,
                        temperatureCelsius = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.ClearSky

                    ),WeatherData(
                        time = time,
                        temperatureCelsius = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.DenseDrizzle
                    ), WeatherData(
                        time = time,
                        temperatureCelsius = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.DenseFreezingDrizzle


                    )
                )
            ),
            WeatherDataPerDay(
                day = 2,
                listOf(
                    WeatherData(
                        time = time,
                        temperatureCelsius = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavyHailThunderstorm

                    ),WeatherData(
                        time = time,
                        temperatureCelsius = 27.0,
                        pressure = 180.0,
                        windSpeed = 11.0,
                        humidity = 30.0,
                        weatherType = WeatherType.HeavySnowFall
                    ), WeatherData(
                        time = time,
                        temperatureCelsius = 27.0,
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