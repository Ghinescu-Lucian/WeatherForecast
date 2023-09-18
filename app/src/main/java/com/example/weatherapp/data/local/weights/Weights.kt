package com.example.weatherapp.data.local.weights

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weights")
data class Weights (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "OpenMeteo") val omWeight: Double,
    @ColumnInfo(name =  "AccuWeather") val accWeight: Double,
    @ColumnInfo(name =  "VisualCrossing") val vcWeight: Double,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double
)
