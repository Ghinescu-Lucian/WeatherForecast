package com.example.weatherapp.ui.states

import com.example.weatherapp.data.local.weights.Weights

data class PointsState (
    val points: List<Weights>? = null,
    val curentPoint : Weights?= null
)