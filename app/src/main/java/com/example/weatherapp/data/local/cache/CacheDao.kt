package com.example.weatherapp.data.local.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheDao {
    @Query("SELECT * FROM cache")
    fun getAllElements(): Flow<List<Cache>>

    @Insert()
    suspend fun insert(cache: Cache)

    @Update
    suspend fun update(cache: Cache)

    @Query("DELETE FROM cache")
    suspend fun deleteAll()
}