package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.data.remote.accuWeather.AccuWeatherApi
import com.example.weatherapp.data.remote.accuWeather.RetrofitHelperAccuWeather
import com.example.weatherapp.data.remote.openMeteo.OpenMeteoApi
import com.example.weatherapp.data.remote.visualCrossing.RetrofitHelperVisual_Crossing
import com.example.weatherapp.data.remote.visualCrossing.VisualCrossingApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideWeatherApi(): OpenMeteoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideVisualCrossingApi(): VisualCrossingApi{
        return  RetrofitHelperVisual_Crossing.getInstance().create()
    }

    @Provides
    @Singleton
    fun provideAccuWeatherApi(): AccuWeatherApi{
        return RetrofitHelperAccuWeather.getInstance().create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }

    // sa pun bind pentru ClockInterface

}