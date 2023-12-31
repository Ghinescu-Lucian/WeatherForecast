package com.example.weatherapp.ui.viewModels


//import dagger.hilt.android.lifecycle.HiltViewModel
import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.cache.CacheRepository
import com.example.weatherapp.data.local.cache.json.dtos.CacheConverter
import com.example.weatherapp.ui.states.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//import javax.inject.Inject

// interfata pentru a lua si a transforma din C in F



@HiltViewModel
class OfflineViewModel @Inject constructor (
    application : Application,
   val  cacheRepository: CacheRepository
) : MainViewModel(application) {

//    stateFlow

    init{
        viewModelScope.launch {

            cacheRepository.allCaches.collect{
                val r = it.lastOrNull()
                if(r != null) {
                    val res = CacheConverter().convertToWeatherInfo(r)
                    _state.update {
                        WeatherState(
                            cityName = r?.city,
                            error = "",
                            isLoading = false,
                            weatherInfo = res
                        )
                    }
                }

            }

            val r = cacheRepository.allCaches.first()


        }
    }

    override fun refresh(){

    }


}








