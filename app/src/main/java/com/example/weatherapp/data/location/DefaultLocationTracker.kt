package com.example.weatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import java.security.InvalidParameterException
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor (
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): Result<Location> {

        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(
            Context.LOCATION_SERVICE) as LocationManager

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessCoarseLocationPermission){
            return Result.failure(InvalidParameterException("Don't have right permissions.lll"))
        }
        if(!hasAccessFineLocationPermission){
            return Result.failure(InvalidParameterException("Don't have right permissions.2"))
        }
        if(!isGpsEnabled){
            return Result.failure(InvalidParameterException("Don't have right permissions.3"))
        }

//        val location = Location("")
//        location.latitude = 45.7537
//        location.longitude = 21.2257
//
//        return Result.success(location)

          return suspendCancellableCoroutine { cont ->
                    locationClient.lastLocation.apply{
                        if(isComplete){
                            if(isSuccessful) {
                                cont.resume(Result.success(result))
                            }
                            else {
                                cont.resume(Result.failure(Exception("Location tracker job failed.")))
                            }
                            return@suspendCancellableCoroutine
                        }
                        addOnSuccessListener {
                    cont.resume(Result.success(it))
                }
                addOnFailureListener{
                    cont.resume(Result.failure(Exception("Job failed.")))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}