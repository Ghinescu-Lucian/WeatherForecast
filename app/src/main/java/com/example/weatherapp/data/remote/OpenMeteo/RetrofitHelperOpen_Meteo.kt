package com.example.weatherapp.data.remote.OpenMeteo

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelperOpen_Meteo {

   fun getInstance(): Retrofit {
       return Retrofit.Builder().baseUrl("https://api.open-meteo.com/")
           .addConverterFactory(MoshiConverterFactory.create())
            .build()

   }
}