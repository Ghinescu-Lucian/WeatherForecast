package com.example.weatherapp.data.remote.visualCrossing

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelperVisual_Crossing {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}