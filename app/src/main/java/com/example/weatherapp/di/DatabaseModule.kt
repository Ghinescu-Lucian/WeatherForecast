package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.cache.CacheDB
import com.example.weatherapp.data.local.cache.CacheDao
import com.example.weatherapp.data.local.cache.CacheRepository
import com.example.weatherapp.data.local.weights.WeightRepository
import com.example.weatherapp.data.local.weights.WeightsDB
import com.example.weatherapp.data.local.weights.WeightsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
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
    @Singleton
    fun providesWeightsDao(@ApplicationContext context: Context): WeightsDao{
        val db = provideDB(context)
        return db.weightsDao()
    }

    @Provides
    @Singleton
    fun provideWeightsRepository(@ApplicationContext context: Context): WeightRepository{
        return WeightRepository(providesWeightsDao(context))
    }

    @Provides
    @Singleton
    fun provideCacheDB(@ApplicationContext context: Context) : CacheDB{
        return Room.databaseBuilder(
            context.applicationContext,
            CacheDB::class.java,
            "cacheDB"
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesCacheDao(@ApplicationContext context: Context): CacheDao {
        val db = provideCacheDB(context)
        return db.CacheDao()
    }

    @Provides
    @Singleton
    fun provideCacheRepository(@ApplicationContext context: Context): CacheRepository {
        return CacheRepository(providesCacheDao(context))
    }
}