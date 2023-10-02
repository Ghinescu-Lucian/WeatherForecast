package com.example.weatherapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.ui.states.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


abstract class MainViewModel  (
    application : Application,
) : AndroidViewModel(application) {

    protected val _state = MutableStateFlow(WeatherState())
    val point =  Point

    val state : StateFlow<WeatherState> = _state.asStateFlow()

    abstract fun refresh()

}
