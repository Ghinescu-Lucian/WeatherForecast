package com.example.weatherapp.data.local.weights

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/*


 */
@Database(entities = arrayOf(Weights::class), version = 1, exportSchema = false)
public abstract class WeightsDB : RoomDatabase() {

    abstract fun weightsDao(): WeightsDao

    companion object {
        @Volatile
        private var INSTANCE: WeightsDB? = null

        fun getDatabase(context: Context) : WeightsDB {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeightsDB::class.java,
                    "weights_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}