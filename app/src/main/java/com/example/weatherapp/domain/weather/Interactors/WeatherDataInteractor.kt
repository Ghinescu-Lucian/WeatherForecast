package com.example.weatherapp.domain.weather.Interactors

import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime

interface WeatherDataInteractor {
    suspend fun getWeatherData(): Result<WeatherData> //
    suspend fun getWeatherDataPerDay(days: Int = 12): Result<List<WeatherDataPerDay>>
}

class WeatherDataInteractorImpl : WeatherDataInteractor {
    override suspend fun getWeatherData(): Result<WeatherData> {

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

    override suspend fun getWeatherDataPerDay(days: Int): Result<List<WeatherDataPerDay>> {

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