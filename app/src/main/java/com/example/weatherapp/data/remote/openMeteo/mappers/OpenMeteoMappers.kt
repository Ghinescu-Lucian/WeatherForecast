package com.example.weatherapp.data.remote.openMeteo.mappers

import com.example.weatherapp.data.remote.openMeteo.dto.curentHourlyWeather.WeatherDataDto
import com.example.weatherapp.data.remote.openMeteo.dto.curentHourlyWeather.WeatherDto
import com.example.weatherapp.data.remote.openMeteo.dto.dailyWeather.OpenMeteoDailyDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



// mai am de lucru aici

fun WeatherDataDto.toWeatherDataPerDay() : List<WeatherDataPerDay>{

    val list = mutableListOf<WeatherDataPerDay>()

    for(i in 0..6){

        val listWeatherData = List(24){
            WeatherData(
                time = LocalDateTime.parse(time[it+i*24], DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperatures[it+i*24],
                pressure = pressures[it+i*24],
                windSpeed = windSpeeds[it+i*24],
                humidity =  humidities[it+i*24],
                weatherType = WeatherType.fromWMO(weatherCodes[it+i*24])

            )
        }

        list.add(WeatherDataPerDay(i, forecasts = listWeatherData))

//        Log.d("Mapper1",listWeatherData.toString())

    }

    return list

//    return time.mapIndexed{ index, time ->
//        val temperature = temperatures[index]
//        val weatherCode = weatherCodes[index]
//        val windSpeed = windSpeeds[index]
//        val pressure = pressures[index]
//        val humidity = humidities[index]
//
//        WeatherDataPerDay(
//            day = index,
//            listOf(
//                WeatherData(
//            time  = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
//            temperatureCelsius =  temperature,
//            pressure = pressure,
//            windSpeed = windSpeed,
//            humidity = humidity,
//            weatherType = WeatherType.fromWMO(weatherCode)
//            )
//            )
//        )
//    }

}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataPerDay = weatherData.toWeatherDataPerDay()
    val now = LocalDateTime.now()

    val index = if(now.minute < 30) now.hour else now.hour+1

    val currentWeatherData = weatherDataPerDay[0].forecasts[index]


    return WeatherInfo(
        weatherDataPerDays = weatherDataPerDay,
        currentWeatherData =  currentWeatherData
    )

}

fun OpenMeteoDailyDto.toWeatherInfo(): WeatherInfo{
    val list = mutableListOf<WeatherDataPerDay>()

    for(index in 0..4)
        list.add(
            WeatherDataPerDay(
                day = index,
                minTemperature =  days.temperaturesMin[index],
                maxTemperature =  days.temperaturesMax[index],
                sunRise =   days.sunrises[index],
                sunSet =  days.sunsets[index],
                moonRise = "",
                moonSet = "",
                weatherTypeDay = WeatherType.fromWMO( days.weatherCodes[index]),
                forecasts = listOf()
            )
        )
    return WeatherInfo(weatherDataPerDays = list, currentWeatherData = null)
}