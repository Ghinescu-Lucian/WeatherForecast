package com.example.weatherapp.Services

enum class Temperature {
    CELSIUS, FARENHEIT
}

interface TemperaturePreferences{
    fun setTemperature(temperature: Temperature)
    fun getTemperature(): Temperature
}