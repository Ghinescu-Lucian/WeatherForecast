package com.example.weatherapp.data.remote.accuWeather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelperAccuWeather {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}