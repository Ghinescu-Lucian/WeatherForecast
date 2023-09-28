package com.example.weatherapp.data.remote.visualCrossing.mappers


import com.example.weatherapp.data.remote.visualCrossing.dto.DailyDto.VisualCrossingDailyDto
import com.example.weatherapp.data.remote.visualCrossing.dto.current.VisualCrossingCurrentDto
import com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto.VisualCrossingHourlyDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.LocalTime

fun VisualCrossingCurrentDto.toWeatherInfo(): WeatherInfo{

    val date = this.day[0].date
    val dto = this.currentConditions
    var isDay = true
    val time = LocalTime.parse(dto.time)
    if( time.hour < 8 || time.hour > 20) isDay = false

   val current = WeatherData(
       time = LocalDateTime.parse("${date}T${dto.time}").toString(),
       temperature = dto.temperature,
       pressure = dto.pressure,
       humidity = dto.humidity,
       description = dto.description,
       isDay = isDay,
       windSpeed = dto.windspeed,
       weatherType = WeatherType.fromVisualCrossing(dto.weatherCode)
   )

    return WeatherInfo(
        currentWeatherData = current,
        weatherDataPerDays = listOf()
    )


}

fun VisualCrossingHourlyDto.toWeatherInfo():WeatherInfo{
    val list = mutableListOf<WeatherDataPerDay>()
    val list2 = mutableListOf<WeatherData>()

    val date = this.days[0].date

    this.days[0].hours.forEachIndexed { index, dto ->

        var isDay = true
        val time = LocalTime.parse(dto.hour)
        if( time.hour < 8 || time.hour > 20) isDay = false


        list2.add(
            WeatherData(
                time = LocalDateTime.parse("${date}T${dto.hour}").toString(),
                temperature = dto.temperature,
                pressure = dto.pressure,
                windSpeed = dto.windSpeed,
                humidity = dto.humidity,
                isDay = isDay,
                weatherType = WeatherType.fromVisualCrossing(dto.weatherCode)

            )
        )
        list.add(
            WeatherDataPerDay(
                day = 0,
                forecasts = list2
            )
        )
    }

    return WeatherInfo(
        currentWeatherData = null,
        weatherDataPerDays = list
    )


}

fun VisualCrossingDailyDto.toWeatherInfo(): WeatherInfo {
    val weatherDataPerDays = mutableListOf<WeatherDataPerDay>()
    days.forEachIndexed { index, dto ->

           weatherDataPerDays.add( WeatherDataPerDay(
                day = index,
                minTemperature = dto.temperatureMin,
                maxTemperature = dto.temperatureMax,
                sunRise = dto.sunrise,
                sunSet = dto.sunset,
                moonRise = "",
                moonSet = "",
                weatherTypeDay = WeatherType.fromVisualCrossing(dto.weatherCode),
                forecasts = listOf()
            )
           )


    }
    return WeatherInfo(weatherDataPerDays = weatherDataPerDays, currentWeatherData = null)
}