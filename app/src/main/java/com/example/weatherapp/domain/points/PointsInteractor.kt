package com.example.weatherapp.domain.points

import android.util.Log
import com.example.weatherapp.data.local.weights.WeightRepository
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.domain.location.LocationTracker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PointsInteractor @Inject constructor(
    val repository: WeightRepository,
    val locationTracker: LocationTracker
    ) {

    fun getPoints(): Flow<List<Weights>> {
//        Log.d("Points Interactor", "Got here!")
        return repository.allWeights
    }

    suspend fun getCurrentPoint(): Weights {
//        Log.d("Points Interactor", "Got here! Current point.")
        val result = locationTracker.getCurrentLocation()
//        Log.d( "Current point", result.toString())

        var point = Weights(id = 0,"",  omWeight = 1.0, accWeight = 1.0, vcWeight = 1.0, 0.0, 0.0, )
        result.onSuccess{

            point = point.copy(latitude = it!!.latitude, longitude = it.longitude)
        }
//        Log.d("Current point ", point.toString())
        return point
    }


    suspend fun updatePoint(point: Weights){
        repository.update(point)
    }

    suspend fun deletePoint(point: Weights){
        repository.delete(point)
    }

    suspend fun addPoint(point: Weights){

        repository.insert(point)
    }
}