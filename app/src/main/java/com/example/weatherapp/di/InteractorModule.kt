package com.example.weatherapp.di

import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractor
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    @Singleton
    abstract fun bindInteractor(weatherDataInteractorImpl: WeatherDataInteractorImpl) : WeatherDataInteractor
}