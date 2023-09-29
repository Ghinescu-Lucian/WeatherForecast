package com.example.weatherapp.domain.weather.Interactors

import android.util.Log
import com.example.weatherapp.data.local.weights.Weights
import com.google.android.gms.maps.model.LatLng
import kotlin.math.min
import kotlin.math.sqrt

class DistanceCalculator {
}
fun DistanceCalculator.getClosestPoint(points: List<Weights>, currentPositon: LatLng): Weights {
    var closest = points[0]
    var dmin = calculateDistance(points[0], currentPositon)
    Log.d("Distance:", currentPositon.toString())
    Log.d("Distance:",dmin .toString())
    points.forEachIndexed{ index, it ->
        if(index != 0){
            val d = calculateDistance(it, currentPositon)
            Log.d("Distance:",d.toString())
            if(dmin > d){
                closest = it
                dmin = d
            }
        }
    }

    Log.d("Closest:", closest.city)

    return closest
}

fun calculateDistance(x: Weights, y:LatLng): Double{
    var y1=0.0
    var y2 =0.0
    val dist1 = sqrt(Math.pow(x.latitude - y.latitude, 2.0) + Math.pow(x.longitude - y.longitude, 2.0))
    if(x.longitude > 0) {
        y1 = 180-x.longitude
    } else y1 = -180 - x.longitude
    if(y.longitude > 0)
        y2 = 180 - y.longitude
    else y2 = -180 - y.longitude
    val dist2 = sqrt(Math.pow(x.latitude - y.latitude, 2.0) + Math.pow(y1 - y2, 2.0))

    return  min(dist1, dist2)
}