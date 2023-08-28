package com.example.weatherapp.di

import com.example.weatherapp.data.remote.accuWeather.repository.AccuWeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.data.remote.openMeteo.repository.WeatherRepositoryOpenMeteoImpl
import com.example.weatherapp.data.remote.visualCrossing.repository.VisualCrossingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    @Named("OpenMeteo")
    abstract fun bindWeatherRepository(weatherRepositoryOpenMeteoImpl: WeatherRepositoryOpenMeteoImpl): WeatherRepository

    @Binds
    @Singleton
    @Named("VisualCrossing")
    abstract fun bindVisualCrossingrRepository(visualCrossingRepositoryImpl: VisualCrossingRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    @Named("AccuWeather")
    abstract fun bindAccuWeatherRepository(accuWeatherRepositoryImpl: AccuWeatherRepositoryImpl) : WeatherRepository
}