package com.example.weatherapp.data.local.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache")
data class Cache (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "City") val city: String,
    @ColumnInfo(name = "partialResult") val partialResult: Boolean,
    @ColumnInfo(name = "data") val data: String
)