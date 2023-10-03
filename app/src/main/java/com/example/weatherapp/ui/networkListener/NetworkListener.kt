package com.example.weatherapp.ui.networkListener

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import com.example.weatherapp.ui.viewModels.WeatherViewModel

class NetworkListener(connectivitytService: String, context : Context, val viewModel : WeatherViewModel) {

    val connectivityManager = context.getSystemService(connectivitytService) as ConnectivityManager
    val builder = NetworkRequest.Builder()




    fun register(){

        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    // Network is available
                    viewModel.informOnline()
                    Log.d("Networkul", "is available")
                }

                override fun onLost(network: Network) {
                    // Network is lost
                    viewModel.informOffline()
                    Log.d("Networkul", "is lost")
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    // Network capabilities changed
                }
            }
        )
    }
}