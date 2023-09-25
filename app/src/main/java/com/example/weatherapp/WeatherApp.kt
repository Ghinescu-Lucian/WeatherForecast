package com.example.weatherapp

import android.app.Application
import android.util.Log
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.data.local.weights.WeightRepository
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

//    val database by lazy { WeightsDB.getDatabase(this) }
//    val repository by lazy { WeightRepository(database.weightsDao())}

        override fun onCreate() {
        super.onCreate()

            val citySearch = CitySearch()
            val response = citySearch.retrieveCoordinates("Novigrad",this)
            Log.d("CitySearch", response.toString())

//            val wg = Weights(id = 2, "Munich",1.0, 1.0, 1.0, 48.142286, 11.579616)

            GlobalScope.launch {
//
//                repository.insert(wg)
                repository.allWeights.collect{ it ->
                    Log.d("Database", it.size.toString())
                    it.forEach{itt ->
                        Log.d("Database", itt.toString())
                    }

                }





            }



    }
}