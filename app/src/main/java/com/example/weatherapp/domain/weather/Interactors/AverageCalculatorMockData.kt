package com.example.weatherapp.domain.weather.Interactors

import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.domain.weather.WeatherType.ClearSky
import java.time.LocalDateTime

class AverageCalculatorMockData {

    val weatherInfoOpenMeteo = WeatherInfo(
        currentWeatherData = WeatherData(
            time = LocalDateTime.now(),
            temperature = 25.0,
            pressure = 1000.0,
            humidity = 50.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 10.0
        ),
        weatherDataPerDays = listOf(
            WeatherDataPerDay(
                day =0, 
                minTemperature = 10.0,
                maxTemperature = 30.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = WeatherType.ClearSky,
                weatherTypeNight = ClearSky,

                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =1, 
                minTemperature = 10.0,
                maxTemperature = 30.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = WeatherType.Overcast,
                weatherTypeNight = WeatherType.Overcast,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),

                )
            ),
            WeatherDataPerDay(
                day =2, 
                minTemperature = 10.0,
                maxTemperature = 30.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = WeatherType.HeavyRain,
                weatherTypeNight = WeatherType.HeavyRain,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),

                )
            ),
            WeatherDataPerDay(
                day =3,
                minTemperature = 10.0,
                maxTemperature = 30.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = WeatherType.ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),

                )
            ),
            WeatherDataPerDay(
                day =4,
                minTemperature = 10.0,
                maxTemperature = 30.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = WeatherType.ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =    listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.0,
                        pressure = 1000.0,
                        humidity = 50.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 10.0
                    ),

                )
            ),
        )
    )

    val weatherInfoAccuWeather = WeatherInfo(
        currentWeatherData = WeatherData(
            time = LocalDateTime.now(),
            temperature = 35.0,
            pressure = 1100.0,
            humidity = 75.0,
            weatherType = WeatherType.fromWMO(3),
            windSpeed = 20.0
        ),
        weatherDataPerDays = listOf(
            WeatherDataPerDay(
                day =0, 
                minTemperature = 15.0,
                maxTemperature = 35.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky ,
                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =1,
                minTemperature = 15.0,
                maxTemperature = 35.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),

                    )
            ),
            WeatherDataPerDay(
                day =2,
                minTemperature = 15.0,
                maxTemperature = 35.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky ,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),

                    )
            ),
            WeatherDataPerDay(
                day =3,
                minTemperature = 15.0,
                maxTemperature = 35.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky ,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),

                    )
            ),
            WeatherDataPerDay(
                day =4,
                minTemperature = 15.0,
                maxTemperature = 35.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky ,
                forecasts =    listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 35.0,
                        pressure = 1100.0,
                        humidity = 75.0,
                        weatherType = WeatherType.fromWMO(65),
                        windSpeed = 20.0
                    ),
                    )
            ),
        )
    )
    val weatherInfoVisualCrossing = WeatherInfo(
        currentWeatherData = WeatherData(
            time = LocalDateTime.now(),
            temperature = 30.0,
            pressure = 1050.0,
            humidity = 100.0,
            weatherType = WeatherType.fromWMO(3),
            windSpeed = 30.0
        ),
        weatherDataPerDays = listOf(
            WeatherDataPerDay(
                day =0, 
                minTemperature = 5.0,
                maxTemperature = 25.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky,
                forecasts = listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    )

                )
            ),
            WeatherDataPerDay(
                day =1,
                minTemperature = 5.0,
                maxTemperature = 25.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),

                    )
            ),
            WeatherDataPerDay(
                day =2,
                minTemperature = 5.0,
                maxTemperature = 25.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),

                    )
            ),
            WeatherDataPerDay(
                day =3,
                minTemperature = 5.0,
                maxTemperature = 25.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),

                    )
            ),
            WeatherDataPerDay(
                day =4,
                minTemperature = 5.0,
                maxTemperature = 25.0,
                sunRise = "10:00",
                sunSet = "20:00",
                moonRise = "19:00",
                moonSet = "9:00",
                weatherTypeDay = ClearSky,
                weatherTypeNight = ClearSky,
                forecasts =    listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 30.0,
                        pressure = 1050.0,
                        humidity = 100.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 30.0
                    ),
                )
            ),
        )
    )

    fun getData(): List<Result<WeatherInfo>>{
        return listOf(
            Result.success(weatherInfoOpenMeteo),
            Result.success(weatherInfoVisualCrossing),
            Result.success(weatherInfoAccuWeather)
        )
    }
}