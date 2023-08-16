package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractor
import com.example.weatherapp.domain.weather.WeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// interfata pentru a lua si a transforma din C in F

class WeatherViewModel(val interactor: WeatherDataInteractor) : ViewModel() {

//    stateFlow

   private  val _state = MutableStateFlow(WeatherState())

    val state : StateFlow<WeatherState> = _state.asStateFlow()


    init{
        viewModelScope.launch{
            // pasez in constructor interactorul meu
            val result = interactor.getWeatherData().onSuccess{ data ->
                Log.d("DEBUG1", "CEVA din viewModel $data")

                _state.update{
                    Log.d("DEBUG1",data.toString())
                    it.copy(
                        weatherInfo = WeatherInfo(
                            currentWeatherData = data,
                            weatherDataPerDay =listOf()
                        )
                    )
                }
                Log.d("DEBUG1", _state.value.toString())
            }

        }

        viewModelScope.launch{
            val result = interactor.getWeatherDataPerDay().onSuccess{ dataPerDay ->
                _state.update{
                    it.copy(
                        weatherInfo = it.weatherInfo?.copy(weatherDataPerDay = dataPerDay)
                    )
                }
            }
        }
    }



//    fun loadWeatherInfo(){
////
//        val result = interactor.getWeatherData().onSuccess {
//            data -> _state.update {
//                it.copy(
//                    weatherInfo = it.weatherInfo?.copy(currentWeatherData = data)
//                )
//            }
//        }
//    }

}