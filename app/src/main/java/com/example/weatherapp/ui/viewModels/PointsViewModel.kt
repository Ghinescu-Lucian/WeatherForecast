package com.example.weatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.domain.points.PointsInteractor
import com.example.weatherapp.ui.states.PointsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

object PointOnMap {
    private var point = Weights(0, "",1.0,1.0,1.0,-181.0,-181.0)
    var isExp: Boolean = true
    fun getPoint(): Weights { return point }
    fun setCity(s: String){
        point = point.copy(city = s)
    }
    fun setLatitutde(lat: Double){
        point = point.copy(latitude = lat)
    }
    fun setLongitude(long: Double){
        point = point.copy(longitude = long)
    }

    fun restore(){
        point = Weights(0, "",1.0,1.0,1.0,-181.0,-181.0)
    }


}

@HiltViewModel
class PointsViewModel @Inject constructor(val pointsInteractor: PointsInteractor ): ViewModel(){

    private val _statePoints= MutableStateFlow(PointsState())
    val statePoints :StateFlow<PointsState> = _statePoints.asStateFlow()
    val point = PointOnMap

    val results = MutableSharedFlow<String?>()

    val updatePoints = mutableListOf<Weights>()
    val deletePoints = mutableListOf<Weights>()

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

    fun setNewPoint(point: Weights){
            _statePoints.update {
                Log.d("Point Ji ", point.toString())
                it.copy(
                    curentPoint =  point
                )

            }
            Log.d("Point Ji ", _statePoints.value.curentPoint.toString())

    }

    fun addUpdatePoint(point: Weights){

        Log.d("Update point:", point.toString())
        Log.d("Update point", updatePoints.toString())

        val check = updatePoints.any{
            it.id == point.id
        }
        if(check){
            updatePoints.removeIf { it.id == point.id }

        }

        updatePoints.add(point)
    }

    fun removeUpdatePoint(point: Weights){
        if(updatePoints.contains(point))
           updatePoints.remove(point)
    }

    fun updatePoints(){
        var result = true
        viewModelScope.launch {
            updatePoints.forEach {
                if(!updatePoint(it))
                    result = false
            }
            if (!result)
                results.emit("Error on updating")
            else
                results.emit("Changes applied")
        }

    }

   private suspend fun updatePoint(point: Weights) : Boolean {

       return try {
           pointsInteractor.updatePoint(point)
           true
       } catch (e: Exception) {
           false
       }
   }

    fun addDeletePoint(point: Weights){
        Log.d("Delete point: ", point.toString())
        Log.d("Delete point: ", deletePoints.toString())
        deletePoints.add(point)
    }
    fun removeDeltePoint(point: Weights){
        if(deletePoints.contains(point))
          deletePoints.remove(point)
    }

    fun deletePoints(){
//        var result = Result.success (true)

        var result = true
        viewModelScope.launch {
            deletePoints.forEach {
                deletePoint(it)
                if (!deletePoint(it))
                    result = false

//                result = Result.failure(Exception("Error occurred when updating points."))
            }

            if (!result)
                results.emit("Error on deleting")
            else
                results.emit("Successfully deleted")
        }

    }

   private suspend fun deletePoint(point: Weights): Boolean{

            return try {
                pointsInteractor.deletePoint(point)
                true
            }catch(e : Exception){
                false
            }



    }

    fun addPoint(point: Weights){

        Log.d("Add point:1", point.toString())
       var result = true
        viewModelScope.launch{

            if(point.longitude == -181.0 )
                results.emit("Error on adding")
            else {

                try {
                    pointsInteractor.addPoint(point)
                    result = true
                    Log.d("Add point:", "Result: $result")

                } catch (e: Exception) {
                    result = false
                }

                if (!result)
                    results.emit("Error on adding")
                else
                    results.emit("Successfully added")
            }

        }


    }



}