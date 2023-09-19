package com.example.weatherapp.ui.states


data class SearchState (
     var cityName: String= "",
     var latitude: Double = 0.0,
     var longitude: Double = 0.0,
     val errors: MutableList<String> = mutableListOf()
)