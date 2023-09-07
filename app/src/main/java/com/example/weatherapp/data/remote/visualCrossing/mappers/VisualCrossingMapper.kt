package com.example.weatherapp.data.remote.visualCrossing.mappers


import com.example.weatherapp.data.remote.visualCrossing.dto.DailyDto.VisualCrossingDailyDto
import com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto.VisualCrossingDaysDataDto
import com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto.VisualCrossingDto
import com.example.weatherapp.data.remote.visualCrossing.dto.hourlyDto.VisualCrossingHourDataDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun VisualCrossingHourDataDto.toWeatherData():WeatherData {


    return WeatherData(
        // nu se poate mock-ui time
                time = LocalTime.parse(time).atDate(LocalDate.now()),
                temperature = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromVisualCrossing(weatherCode)

            )
}
fun VisualCrossingDaysDataDto.toWeatherDataPerDay():List<WeatherDataPerDay> {
    val list = mutableListOf<WeatherDataPerDay>()

    val list2 = mutableListOf<WeatherData>()
    for(hour in hours) {
        list2.add(hour.toWeatherData())
    }
    list.add(WeatherDataPerDay(0, forecasts = list2))

//    for (i in 0..6) {
//
////        val listWeatherData = List(24) {
////            WeatherData(
////                time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(),//, DateTimeFormatter.ISO_LOCAL_DATE),
////                temperatureCelsius = temperatureMax,
////                pressure = pressure,
////                windSpeed = windSpeed,
////                humidity = humidity,
////                weatherType = WeatherType.fromVisualCrossing(weatherCode)
////
////            )
//        }


    return list
}

fun VisualCrossingDto.toWeatherInfo(): WeatherInfo {
    val weatherDataPerDay = weatherData[0].toWeatherDataPerDay()
    val now = LocalDateTime.now()

    val index = if(now.minute < 30) now.hour else now.hour+1

    val currentWeatherData = weatherDataPerDay[0].forecasts[index]


    return WeatherInfo(
        weatherDataPerDay = weatherDataPerDay,
        currentWeatherData =  currentWeatherData
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
    return WeatherInfo(weatherDataPerDay = weatherDataPerDays, currentWeatherData = null)
}