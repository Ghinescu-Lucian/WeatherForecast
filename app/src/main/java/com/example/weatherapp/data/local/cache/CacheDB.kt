package com.example.weatherapp.data.local.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Cache::class), version = 1, exportSchema = true)
public abstract class CacheDB : RoomDatabase() {
    abstract fun CacheDao(): CacheDao

    companion object {
        @Volatile
        private var INSTANCE: CacheDB? = null

        fun getDatabase(context: Context) : CacheDB {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   CacheDB::class.java,
                    "cacheDB"
                )

                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}