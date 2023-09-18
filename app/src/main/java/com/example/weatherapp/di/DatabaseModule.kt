package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.weights.WeightRepository
import com.example.weatherapp.data.local.weights.WeightsDB
import com.example.weatherapp.data.local.weights.WeightsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideDB(@ApplicationContext context: Context) : WeightsDB{
        return  Room.databaseBuilder(
            context.applicationContext,
            WeightsDB::class.java,
            "weights_database"
        )
            .createFromAsset("database/sqlite.db")
            .build()
    }

    @Provides
    fun providesWeightsDao(@ApplicationContext context: Context): WeightsDao{
        val db = provideDB(context)
        return db.weightsDao()
    }

    @Provides
    fun provideWeightsRepository(@ApplicationContext context: Context): WeightRepository{
        return WeightRepository(providesWeightsDao(context))
    }
}