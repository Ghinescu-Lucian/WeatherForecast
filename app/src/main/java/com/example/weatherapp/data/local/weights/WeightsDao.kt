package com.example.weatherapp.data.local.weights

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// vad daca am points
@Dao
interface WeightsDao{
    @Query("SELECT * FROM weights")
    fun getAllPoints(): Flow<List<Weights>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weights: Weights)
    @Update//(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun update(weights: Weights)
    @Query(" DELETE FROM weights WHERE id = :weight_id")
    suspend fun deleteById(weight_id: Int)
    @Query("DELETE FROM weights")
    suspend fun deleteAll()

}