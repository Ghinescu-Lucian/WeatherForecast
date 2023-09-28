package com.example.weatherapp.ui.viewModels

//import dagger.hilt.android.lifecycle.HiltViewModel
import android.app.Application
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractor
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.ui.states.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//import javax.inject.Inject

// interfata pentru a lua si a transforma din C in F

object Point{
    var location: Location = Location("")
    var cityName: String = ""
}

@HiltViewModel
class WeatherViewModel @Inject constructor (
    val interactor: WeatherDataInteractor,
    application : Application
    ) : AndroidViewModel(application) {

//    stateFlow

    private val _state = MutableStateFlow(WeatherState())
    val point =  Point

    val state : StateFlow<WeatherState> = _state.asStateFlow()
    val context: Context = getApplication()

    init{
        initialize(refresh = true)
        }

    fun refreshContentSearch(refreshCity: Boolean, cityName: String, lat: Double, long: Double) {
            point.cityName = cityName
            point.location.latitude = lat
            point.location.longitude = long

            Log.d("Refresh Called", point.cityName.toString())
            initialize(refreshCity)
            Log.d("State aftr. refresh", _state.value
            .toString())

    }



    fun initialize(refresh: Boolean){
        viewModelScope.launch {
            // pasez in constructor interactorul meu
            _state.update {
                it.copy(
                    isLoading = true
                )
            }


            if(refresh) {
                Log.d("Refresh City", "REFRESH CURRENT CITY")
                interactor.getCityName(context = context).onSuccess { res ->
                    _state.update {
                        point.cityName = res
                        it.copy(
                            cityName = res

                        )
                    }
                }



                interactor.getCoordinates().onSuccess {
                    if (it != null) {
                        point.location.latitude = it.latitude
                        point.location.longitude = it.longitude
                    }
                }

            }
            Log.d("Location: ", point.location.toString())

            val result = interactor.getWeatherData(location = point.location, refresh = !refresh)
            result.onFailure {
                _state.update { itt ->
                    itt.copy(
                        error = it.message,
                        isLoading = false
                    )
                }
            }
            result.onSuccess { data ->
//                Log.d("DEBUG1", "CEVA din viewModel $data")

                _state.update {
//                    Log.d("DEBUG1",data.toString())
                    it.copy(
                        weatherInfo = WeatherInfo(
                            currentWeatherData = data,
                            weatherDataPerDays = listOf()
                        ),
                        isLoading = false
                    )
                }
//                Log.d("DEBUG1", _state.value.toString())
            }
            Log.d("GET DATA: ", result.toString())
            Log.d("Location: ", point.location.toString())

            interactor.getWeatherDataPerDay(location = point.location, refresh = !refresh).onSuccess { dataPerDay ->

                Log.d("ViewModel", dataPerDay.getOrNull(0)?.day.toString())
                _state.update {
                    it.copy(
                        weatherInfo = it.weatherInfo?.copy(weatherDataPerDays = dataPerDay)
                    )
                }
                Log.d("ViewModel", _state.value.weatherInfo?.weatherDataPerDays.toString())
            }

            if(point.cityName.isNotEmpty()) {
                _state.update {
                    it.copy(cityName = point.cityName)
                }
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

