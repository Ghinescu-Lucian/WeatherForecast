package com.example.weatherapp.domain.weather.Interactors

import android.util.Log
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

// @Inject constructor
class AverageCalculator @Inject constructor(

    val weatherRepositories : Set<WeatherRepository>,
    val weights: List<Double>


){
//  : Result<WeatherInfo>
    suspend fun calculateAverage(lat: Double, long: Double): WeatherInfo = coroutineScope{


//      CURRENT CONDITIONS

//       val allDeferred= weatherRepositories.map{
//            async {
//                it.getCurrentWeatherData(lat, long)
//            }
//        }.toTypedArray()
//        val allResults = awaitAll(*allDeferred)

        val allResults = AverageCalculatorMockData().getData()

       val resultCurrent = calculateCurrentConditions(allResults, weights = weights)
       Log.d("AverageCalculator Current",resultCurrent.toString())



//      HOURLY FORECASTS

//        val allDeferredHourly = weatherRepositories.map{
//            async{
//                it.getHourlyWeatherData(lat,long)
//            }
//        }.toTypedArray()
//        val allResultsHourly = awaitAll(*allDeferredHourly)

    val allResultsHourly = allResults

        val resultHourly = calculateHourly(allResultsHourly, weights = weights)
        Log.d("AverageCalculator Hourly", resultHourly.toString())



//        Log.d("AverageCalculator" , allResults[1].isSuccess.toString())
        val isPartialResult = allResults.any{
            it.isFailure
        }


//    DAILY FORECASTS

//    val allDeferedDaily = weatherRepositories.map{
//        async{
//            it.getDailyWeatherData(lat,long)
//        }
//    }.toTypedArray()
//    val allResultDaily = awaitAll(*allDeferedDaily)

    val allResultDaily = allResults
    val resultDaily = calculateDaily(data = allResultDaily, weights = weights)
    Log.d("AverageCalculator Daily",resultDaily.toString())

    val resultDailyHourly = mutableListOf<WeatherDataPerDay>()

    resultDaily.forEachIndexed{index, data ->
        if(index == 0)
            resultDailyHourly.add(
                WeatherDataPerDay(
                    day = data.day,
                    minTemperature = data.minTemperature,
                    maxTemperature = data.maxTemperature,
                    sunRise = data.sunRise,
                    sunSet = data.sunSet,
                    moonRise = data.moonRise,
                    moonSet = data.moonSet,
                    weatherTypeDay = data.weatherTypeDay,
                    weatherTypeNight = data.weatherTypeNight,
                    forecasts = resultHourly.forecasts
                )
            )
        else resultDailyHourly.add(data)
    }


    val finalResult = WeatherInfo(
        currentWeatherData = resultCurrent,
        partialResult = isPartialResult,
        weatherDataPerDays = resultDailyHourly

    )

    return@coroutineScope finalResult

    }


}

fun calculateCurrentConditions(data: List<Result<WeatherInfo>>, weights: List<Double>) : WeatherData{

    val successData = mutableListOf<WeatherData>()
    val successWeights = mutableListOf<Double>()

    data.forEachIndexed { index, result ->
        result.onSuccess {
            successData.add(it.currentWeatherData!!)
            successWeights.add(weights[index])
        }
    }

    return calculateParams(data = successData, weights = successWeights)

}


fun calculateHourly(data: List<Result<WeatherInfo>>, weights: List<Double>) : WeatherDataPerDay{

    val successData = mutableListOf<WeatherDataPerDay>()
    val successWeights = mutableListOf<Double>()

    data.forEachIndexed { index, result ->
        result.onSuccess {
            successData.add(it.weatherDataPerDays[0])
            successWeights.add(weights[index])

        }
    }
    val res = mutableListOf<WeatherData>()
    var minimumSize = successData[0].forecasts.size
    successData.forEach{
        if( it.forecasts.size < minimumSize){
            minimumSize = it.forecasts.size
        }
    }

    val successForecasts = mutableListOf<WeatherData>()


    for(i in 0..(minimumSize -1)){
        successData.forEach{
            successForecasts.add(it.forecasts[i])
        }
        res.add(
            calculateParams(data = successForecasts,
                weights = successWeights
            )
        )
        successForecasts.clear()
    }

    return  WeatherDataPerDay(
        day = successData[0].day,
        sunRise = successData[0].sunRise,
        sunSet = successData[0].sunSet,
        weatherTypeDay = successData[0].weatherTypeDay,
        forecasts = res
    )
}
fun calculateDaily(data: List<Result<WeatherInfo>>, weights: List<Double>) : List<WeatherDataPerDay>{
    val successData = mutableListOf<List<WeatherDataPerDay>>()
    val successWeights = mutableListOf<Double>()


    data.forEachIndexed { index, result ->
        result.onSuccess {
            successData.add(it.weatherDataPerDays)
            successWeights.add(weights[index])
        }
    }

    var minimumSize = successData[0].size
    successData.forEach{
        if( it.size < minimumSize){
            minimumSize = it.size
        }
    }
   // Log.d("Average Daily",minimumSize.toString())
    val sumWeights = successWeights.sum()

    val res = mutableListOf<WeatherDataPerDay>()
    var minTemperature = 0.0
    var maxTemperature = 0.0


    var dayCodes = mutableListOf<WeatherType>()
    var nightCodes = mutableListOf<WeatherType>()

    for(i in 0..(minimumSize-1)){
        minTemperature = 0.0
        maxTemperature = 0.0
        successData.forEachIndexed { index, data ->
            minTemperature += data[i].minTemperature * successWeights[index]
            maxTemperature += data[i].maxTemperature * successWeights[index]
            dayCodes.add(data[i].weatherTypeDay)
            nightCodes.add(data[i].weatherTypeNight)
        }
//        Log.d("Average Daily",minTemperature.toString()+ " \n "+maxTemperature.toString())

        minTemperature /= sumWeights
        maxTemperature /= sumWeights
//        Log.d("Average Daily",minTemperature.toString()+ " \n "+maxTemperature.toString())

        res.add(
            WeatherDataPerDay(
                day = successData[0][i].day,
                minTemperature = minTemperature,
                maxTemperature = maxTemperature,
                sunRise = successData[0][i].sunRise,
                sunSet = successData[0][i].sunSet,
                moonRise = successData[0][i].moonRise,
                moonSet = successData[0][i].moonSet,
                weatherTypeDay = calculateWeatherCode(dayCodes, successWeights),
                weatherTypeNight = calculateWeatherCode(nightCodes, successWeights),
                forecasts = listOf()
            )
        )
        dayCodes.clear()
        nightCodes.clear()
    }



    return res

}

fun calculateParams(data: List<WeatherData>, weights: List<Double>): WeatherData{
    val weightsSum = weights.sum()
    var temperature  = 0.0
    var humidity = 0.0
    var pressure = 0.0
    var windSpeed = 0.0
    var time = data[0].time
    var description = data[0].description

    data.forEachIndexed{ index, it ->
        temperature += it.temperature*weights[index]
        humidity += it.humidity*weights[index]
        pressure +=it.pressure*weights[index]
        windSpeed += it.windSpeed*weights[index]
    }

    temperature /= weightsSum
    humidity /= weightsSum
    pressure /= weightsSum
    windSpeed /= weightsSum

    val weatherCodes = mutableListOf<WeatherType>()
    for(i in data)
        weatherCodes.add(i.weatherType)

    val weatherType = calculateWeatherCode(
        data = weatherCodes,
        weights =  weights
    )

    return WeatherData(
        time = time,
        temperature = temperature,
        humidity = humidity,
        pressure = pressure,
        windSpeed = windSpeed,
        weatherType = weatherType,
        description = description
    )
}

fun calculateWeatherCode(data: List<WeatherType>, weights: List<Double>): WeatherType{
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

            pool[result] = weights[index]+ pool[result]!!

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


