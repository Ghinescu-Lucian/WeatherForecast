package com.example.weatherapp.ui.viewModels

import android.util.Log
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.domain.points.PointsInteractor
import com.example.weatherapp.ui.states.PointsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointsViewModel @Inject constructor(val pointsInteractor: PointsInteractor ): ViewModel(){

    private val _statePoints= MutableStateFlow(PointsState())
    val statePoints :StateFlow<PointsState> = _statePoints.asStateFlow()

    init{
        viewModelScope.launch {
//            Log.d("Points ViewModel", "Got here.")
//            Log.d("Points ViewModel2", pointsInteractor.getCurrentPoint().toString())

            pointsInteractor.getPoints().collect { itt ->
//                 Log.d("Points1", itt.toString())
                 _statePoints.update {
                     it.copy(points = itt)
                 }
             }
            }
        viewModelScope.launch{
//            Log.d("Points", pointsInteractor.getCurrentPoint().toString())
            _statePoints.update {
                it.copy(
                    curentPoint = pointsInteractor.getCurrentPoint()
                )
            }
        }
        }

    fun updatePoint(point: Weights): Result<Weights>{
        var result = Result.failure<Weights>(Exception("Error on updating the point."))
        viewModelScope.launch{
            try {
                pointsInteractor.updatePoint(point)
                result = Result.success(point)
            }catch(e : Exception){
                result = Result.failure(Exception(e.message + "\nError on updating the point."))
            }

        }
        return result
    }

    fun deletePoint(point: Weights): Result<Weights>{
        var result = Result.failure<Weights>(Exception("Error on updating the point."))
        viewModelScope.launch{
            try {
                pointsInteractor.deletePoint(point)
                result = Result.success(point)
            }catch(e : Exception){
                result = Result.failure(Exception(e.message + "\nError on updating the point."))
            }

        }
        return result
    }

    fun addPoint(point: Weights): Result<Weights>{
        var result = Result.failure<Weights>(Exception("Error on updating the point."))
        viewModelScope.launch{
            try {
                pointsInteractor.addPoint(point)
                result = Result.success(point)
            }catch(e : Exception){
                result = Result.failure(Exception(e.message + "\nError on updating the point."))
            }

        }
        return result
    }



}