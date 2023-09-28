package com.example.weatherapp.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.ui.states.SearchState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

//    val searchInteractor: SearchInteractor,
    val citySearch: CitySearch

): ViewModel(){

    private val _stateSearch= MutableStateFlow( SearchState())
    val stateSearch : StateFlow<SearchState> = _stateSearch.asStateFlow()

    fun updateCityName(city: String, context : Context) {
     _stateSearch.update { it.copy(
         cityName  = city
     ) }
    }

    fun getLatLong(context: Context): LatLng {
        val response = citySearch.retrieveCoordinates(_stateSearch.value.cityName, context)
        var result = LatLng(0.0, 0.0)
        if(response.isEmpty()) {
            _stateSearch.value.errors.clear()
            val error = "Couldn't find this city."
            _stateSearch.value.errors.add(error)
            Log.d("Search", "Error")
        }
        else {
            Log.d("Search latLong", " "+ response[0].latitude + " "+ response[0].longitude)
            result  = LatLng(response[0].latitude, response[0].longitude)
            _stateSearch.value.errors.clear()
            _stateSearch.update {
                it.copy(
                    longitude = response[0].longitude,
                    latitude = response[0].latitude
                )
            }
        }

        return result
    }
}