package com.example.weatherapp.ui.mainScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel(){

    fun isTimeOut(time: String): Boolean{

        return difference(time, getCurentTimeFormatted())
    }

    /*
    get two strings of pattern: dd-MMM HH:mm
     */
    private fun difference(time1 : String,time2: String ): Boolean{


        val t1 = LocalTime.parse(time1.takeLast(5))
        val t2 = LocalTime.parse(time2.takeLast(5))

        val duration = Duration.between(t1, t2)
        val timeOut = Duration.ofMinutes(30)

        return duration > timeOut

    }
   private fun getCurentTimeFormatted(): String{
        val format = DateTimeFormatter.ofPattern("dd-MMM HH:mm")
        return LocalDateTime.now().format(format)
    }
}