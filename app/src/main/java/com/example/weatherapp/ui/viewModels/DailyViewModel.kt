package com.example.weatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.ui.states.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor() : ViewModel() {

    fun calculateTemperatureData(data: List<WeatherDataPerDay>): List<List<Float>>{
        val maxTemperatures = mutableListOf<List<Float>>()

        data.forEachIndexed{  index, item ->
            maxTemperatures.add(listOf(index.toFloat(), item.maxTemperature.toFloat()))
        }

        return maxTemperatures

    }

    fun getDaysStrings(state: WeatherState, literal: Boolean): List<String>{

           return getDaysStringsFormatedLiteral(getDays(state), literal)

    }

    fun getDays(state: WeatherState): List<Int>{
        val result = mutableListOf<Int>()

        if(state.weatherInfo != null)
            state.weatherInfo.weatherDataPerDays.forEach{
                result.add(it.day)
            }

        return result
    }

    fun getDaysStringsFormatedLiteral(data : List<Int >, literal: Boolean): List<String>{
        var current = LocalDate.now()
        val period = Period.of(0,0,1)

        val result = mutableListOf<String>()



        var format = DateTimeFormatter.ofPattern("MM", Locale.ENGLISH)
        if(literal)
        {
            result.add("Today: ")
            format = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH)
        }
        else{
            result.add(""+current.dayOfMonth+"."+current.format(format))
        }


        data.forEach{
           current =  current.plus(period)
            val month = current.format(format)
            var delimiter = "."
            if(literal)
                delimiter=" "

            result.add(""+current.dayOfMonth+delimiter+month)

        }

        return result

    }


}