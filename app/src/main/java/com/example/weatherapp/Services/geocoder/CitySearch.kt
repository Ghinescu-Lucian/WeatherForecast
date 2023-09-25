package com.example.weatherapp.Services.geocoder

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
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

   fun getCityName(context: Context, latitude: Double, longitude: Double) : String{
       val geocoder = Geocoder(context)

       val addresses: MutableList<Address>? = geocoder.getFromLocation(latitude, longitude, 1)

       var result= ""

       try{
           if (addresses != null) {
               if (addresses.isNotEmpty()) {
                   // You can retrieve various address details like street, city, country, etc.
                   val address = addresses[0]
                   result = address.locality // City name
               }
           }
       } catch (e: Exception) {
            e.printStackTrace()
            // Handle exceptions (e.g., IOException or IllegalArgumentException)
        }
       return result
   }



}