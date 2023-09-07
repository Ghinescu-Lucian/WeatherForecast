package com.example.weatherapp.domain.weather.Interactors

import android.util.Log
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime
import javax.inject.Inject
// @Inject constructor
class AverageCalculator @Inject constructor(

    val weatherRepositories : Set<WeatherRepository>,
    val weights: List<Double>


){
//  : Result<WeatherInfo>
    suspend fun calculateAverage(lat: Double, long: Double): WeatherInfo = coroutineScope{



       val allDeferred= weatherRepositories.map{
            async {
                it.getCurrentWeatherData(lat, long)
            }
        }.toTypedArray()
        val allResults = awaitAll(*allDeferred)


       val result = calculateCurrentConditions(allResults, weights = weights)
       Log.d("AverageCalculator",result.toString())
       calculateWeatherCode(allResults, weights = weights)
        for(i in allResults ){
            i.onSuccess { it ->
//                Log.d("AverageCalculator",
//                    it.currentWeatherData?.temperature.toString()
//                    )
            }
        }
//        Log.d("AverageCalculator" , allResults[1].isSuccess.toString())
        val isPartialResult = allResults.any{
            it.isFailure
        }

    val weatherInfo = WeatherInfo(
        currentWeatherData = WeatherData(
            time = LocalDateTime.now(),
            temperature = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 12.0
        ),
        weatherDataPerDay = listOf(
            WeatherDataPerDay(
                day =0,
               forecasts =  listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(1),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(2),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.12,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(3),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
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
               forecasts =  listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(2),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
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
              forecasts =   listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(45),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
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
               forecasts =  listOf(
                    WeatherData(
                        time = LocalDateTime.now(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
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
                        time = LocalDateTime.now(),
                        temperature = 25.2,
                        pressure = 1000.0,
                        humidity = 56.0,
                        weatherType = WeatherType.fromWMO(0),
                        windSpeed = 12.0
                    ),
                    WeatherData(
                        time = LocalDateTime.now(),
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

    return@coroutineScope weatherInfo

    }


}

fun calculateCurrentConditions(data: List<Result<WeatherInfo>>, weights: List<Double>) : WeatherData{

    val weightsSum = weights.sum()
    var temperature : Double = 0.0
    var humidity: Double = 0.0
    var pressure: Double = 0.0
    var windSpeed: Double = 0.0

    var time : LocalDateTime = LocalDateTime.now()

    data[0].onSuccess {
        time = it.currentWeatherData!!.time
    }

    data.forEachIndexed { index, result ->
        result.onSuccess {
            temperature = temperature + it.currentWeatherData!!.temperature * weights[index]
            humidity = humidity + it.currentWeatherData.humidity * weights[index]
            pressure = pressure + it.currentWeatherData.pressure * weights[index]
            windSpeed = windSpeed + it.currentWeatherData.windSpeed * weights[index]
        }
    }

    temperature /= weightsSum
    humidity /= weightsSum
    pressure /= weightsSum
    windSpeed /= weightsSum

    val weatherType = calculateWeatherCode(data, weights = weights)

    return WeatherData(
        time = time,
        temperature = temperature,
        humidity =  humidity,
        pressure =  pressure,
        windSpeed = windSpeed,
        weatherType = weatherType
    )

}

fun calculateWeatherCode(data: List<Result<WeatherInfo>>, weights: List<Double>): WeatherType{
    var pool = mutableMapOf(
        WeatherType.ClearSky to 0.0,
        WeatherType.MainlyClear to 0.0,
        WeatherType.PartlyCloudy to 0.0,
        WeatherType.Overcast to 0.0,
        WeatherType.Foggy to 0.0,
        WeatherType.DepositingRimeFog to 0.0,
        WeatherType.LightDrizzle to 0.0,
        WeatherType.ModerateDrizzle to 0.0,
        WeatherType.DenseDrizzle to 0.0,
        WeatherType.LightFreezingDrizzle to 0.0,
        WeatherType.DenseFreezingDrizzle to 0.0,
        WeatherType.SlightRain to 0.0,
        WeatherType.ModerateRain to 0.0,
        WeatherType.HeavyRain to 0.0,
        WeatherType.HeavyFreezingRain to 0.0,
        WeatherType.SlightSnowFall to 0.0,
        WeatherType.ModerateSnowFall to 0.0,
        WeatherType.HeavySnowFall to 0.0,
        WeatherType.SnowGrains to 0.0,
        WeatherType.SlightRainShowers to 0.0,
        WeatherType.ModerateRainShowers to 0.0,
        WeatherType.ViolentRainShowers to 0.0,
        WeatherType.SlightSnowShowers to 0.0,
        WeatherType.HeavySnowShowers to 0.0,
        WeatherType.ModerateThunderstorm to 0.0,
        WeatherType.SlightHailThunderstorm to 0.0,
        WeatherType.HeavyHailThunderstorm to 0.0
    )

    data.forEachIndexed { index, result ->
        result.onSuccess {
            pool[it.currentWeatherData!!.weatherType] = weights[index]+ pool[it.currentWeatherData!!.weatherType]!!
        }
    }

    var weatherType : WeatherType = WeatherType.ClearSky
    var maximum = pool[WeatherType.ClearSky]

    for(i in pool){
        if(i.value > maximum!!){
            maximum = i.value
            weatherType = i.key
        }
    }

    return weatherType

}
