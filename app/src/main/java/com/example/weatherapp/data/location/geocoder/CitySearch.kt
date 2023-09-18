package com.example.weatherapp.data.location.geocoder

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.util.Collections.list
import java.util.Locale

class CitySearch {
    fun retrieveCoordinates(cityName: String, context: Context): List<Address>{
        val geocoder = Geocoder(context, Locale.getDefault())

        var result = listOf<Address>()

        try{
             result = geocoder.getFromLocationName(cityName, 2)!!

        }catch (e: Exception){
            Log.d("GetCordinates", e.message.toString())
        }

        return result
    }
}