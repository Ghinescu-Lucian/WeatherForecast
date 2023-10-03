package com.example.weatherapp.data.local.weights

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// vad daca am points
@Dao
interface WeightsDao{
    @Query("SELECT * FROM weights")
    suspend fun getAllPoints(): List<Weights>

    @Query("SELECT * FROM weights")
    fun getAllPointsFlow(): Flow<List<Weights>>

    @Insert()
    suspend fun insert(weights: Weights)
    @Update//(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun update(weights: Weights)
    @Query(" DELETE FROM weights WHERE id = :weight_id")
    suspend fun deleteById(weight_id: Int)
    @Query("DELETE FROM weights")
    suspend fun deleteAll()

}