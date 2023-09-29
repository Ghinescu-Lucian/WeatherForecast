package com.example.weatherapp.data.local.cache

import androidx.annotation.WorkerThread
import com.example.weatherapp.data.local.weights.Weights
import kotlinx.coroutines.flow.Flow


class CacheRepository  (private val cacheDao: CacheDao){
  //    Room executes all queries on a separate thread
  val allCaches : Flow<List<Cache>> = cacheDao.getAllElements()

//     By default Room runs suspend queries off the main thread, therefore, we don't need to
//     implement anything else to ensure we're not doing long running database work
//     off the main thread.
   @WorkerThread
   suspend fun insert(cache: Cache) {
    cacheDao.insert(cache = cache)
   }
    @WorkerThread
    suspend fun deleteAll() {
        cacheDao.deleteAll()
    }
    @WorkerThread
    suspend fun update(cache: Cache) {
        cacheDao.update(cache)
    }

}


