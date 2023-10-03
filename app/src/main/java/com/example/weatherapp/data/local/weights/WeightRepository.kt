package com.example.weatherapp.data.local.weights

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class WeightRepository  (private val weightsDao: WeightsDao) {
//    Room executes all queries on a separate thread
    val allWeightsFlow : Flow<List<Weights>> = weightsDao.getAllPointsFlow()

    suspend fun allWeights(): List<Weights>{
        return weightsDao.getAllPoints()
    }

//     By default Room runs suspend queries off the main thread, therefore, we don't need to
//     implement anything else to ensure we're not doing long running database work
//     off the main thread.
    @WorkerThread
    suspend fun insert(weights: Weights) {
        weightsDao.insert(weights = weights)
    }


    @WorkerThread
    suspend fun update(weights: Weights) {
        weightsDao.update(weights = weights)
    }


    @WorkerThread
    suspend fun delete(weights: Weights) {
        weights.id?.let { weightsDao.deleteById(weight_id = it) }
    }

}