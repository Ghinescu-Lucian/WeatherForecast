package com.example.weatherapp.ui.states

import java.time.Duration
import java.time.LocalDateTime

object AuthState {
    var isAuthenticated: Boolean = false
    var lastTime: LocalDateTime? = null


    fun timeOut(): Boolean{
        val now = LocalDateTime.now()
        if(lastTime == null) return true
        val t1 = lastTime
        val t2 = now

        val duration = Duration.between(t1, t2)
        val timeOut = Duration.ofMinutes(5)

       val res =  duration > timeOut
        isAuthenticated = res
        return res
    }

}