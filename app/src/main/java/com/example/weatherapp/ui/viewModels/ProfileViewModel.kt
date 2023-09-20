package com.example.weatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.domain.points.PointsInteractor
import com.example.weatherapp.ui.states.PointsState
import com.example.weatherapp.ui.states.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
   val profileInteractor: PointsInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(PointsState())

    val state : StateFlow<PointsState> = _state.asStateFlow()

    init{

        viewModelScope.launch{
            val result = profileInteractor.getPoints()
            lateinit var r : List<Weights>
            result.collect{
                r = it
                _state.update {it  ->
                    it.copy(points = r)
                }
                Log.d("Points", r.toString())
            }



//            Log.d("Points", result.toString())
        }
    }


}