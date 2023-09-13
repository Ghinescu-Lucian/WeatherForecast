package com.example.weatherapp

import android.app.Application
import android.util.Log
import com.example.weatherapp.data.local.weights.WeightRepository
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.data.local.weights.WeightsDB
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class WeatherApp: Application()
{
    @Inject
    lateinit var db: WeightsDB

    @Inject
    lateinit var repository: WeightRepository

    override fun onCreate() {
        super.onCreate()
//        //
////        db = WeightsDB.getDatabase(this)
//       // repository = WeightRepository(db.weightsDao())
//
//        Log.d("Database", repository.allWeights.toString()

        GlobalScope.launch {
            val weight1 =Weights(2, 1.0,1.0,1.0,45.0, 21.212555)
            var r = listOf<Weights>()

//            repository.insert(weight1)
            repository.delete(weight1)

            repository.allWeights.collect{ it ->
                it.forEach{itt ->
                    Log.d("Database", itt.toString())
                }

            }

        }


    }
}