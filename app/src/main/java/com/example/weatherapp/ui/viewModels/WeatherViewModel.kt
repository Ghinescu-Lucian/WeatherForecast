package com.example.weatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractor
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.ui.states.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//import javax.inject.Inject

// interfata pentru a lua si a transforma din C in F
// mai am de lucru la dagger si la hilt + sa fac private locationTracker si repository

@HiltViewModel
class WeatherViewModel @Inject constructor (
    val interactor: WeatherDataInteractor,
    ) : ViewModel() {

//    stateFlow

   private val _state = MutableStateFlow(WeatherState())

    val state : StateFlow<WeatherState> = _state.asStateFlow()


    init{

        viewModelScope.launch{
            // pasez in constructor interactorul meu
            _state.update{
                it.copy(
                    isLoading = true
                )
            }

            val result = interactor.getWeatherData()
            result.onFailure {
                    _state.update{itt->
                        itt.copy(error = it.message,
                            isLoading = false)
                    }
                }
                result.onSuccess{ data ->
//                Log.d("DEBUG1", "CEVA din viewModel $data")

                _state.update{
//                    Log.d("DEBUG1",data.toString())
                    it.copy(
                        weatherInfo = WeatherInfo(
                            currentWeatherData = data,
                            weatherDataPerDay =listOf()
                        ),
                        isLoading = false
                    )
                }
//                Log.d("DEBUG1", _state.value.toString())
            }
             interactor.getWeatherDataPerDay().onSuccess{ dataPerDay ->

                Log.d("ViewModel", dataPerDay[0].day.toString())
                _state.update{
                    it.copy(
                        weatherInfo = it.weatherInfo?.copy(weatherDataPerDay = dataPerDay)
                    )
                }
                Log.d("ViewModel", _state.value.weatherInfo?.weatherDataPerDay.toString())
            }


        }


    }


//
//    fun loadWeatherInfo(){
//        viewModelScope.launch {
//            _state.update { st ->
//                WeatherState(
//                    weatherInfo = st.weatherInfo,
//                    isLoading = true,
//                    error = null
//                )
//            }
//            locationTracker.getCurrentLocation()?.onSuccess { location ->
//                val result = repository.getWeatherData(location!!.latitude, location!!.longitude)
//                if(result.isSuccess){
//                    _state.update{
//                        WeatherState(
//                            weatherInfo = result.getOrNull(),
//                            isLoading = false,
//                            error = null
//                        )
//                    }
//                }
//                else if(result.isFailure){
//                    _state.update{
//                        WeatherState(
//                            weatherInfo = null,
//                            isLoading = false,
//                            error = result.exceptionOrNull()?.message
//                        )
//                    }
//                }
//
//            }
//
//            locationTracker.getCurrentLocation().onFailure{
//                _state.update{
//                    WeatherState(
//                        isLoading = false,
//                        error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
//                    )
//                }
//            }
//
//            }
//
//        }
    }

