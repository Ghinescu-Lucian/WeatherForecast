package com.example.weatherapp.ui.networkListener

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

class NetworkListener(connectivitytService: String, context : Context, val networkCallback:(Boolean)-> Unit) {

    private val connectivityManager = context.getSystemService(connectivitytService) as ConnectivityManager
    private val builder = NetworkRequest.Builder()
    private val networkListener = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            // Network is available
//                    viewModel.informOnline()
            networkCallback(true)
            Log.d("Networkul", "is available")
        }

        override fun onLost(network: Network) {
            // Network is lost
            networkCallback(false)
            Log.d("Networkul", "is lost")
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            // Network capabilities changed
        }
    }



    fun register(){

        connectivityManager.registerNetworkCallback(
            builder.build(),
            networkListener

        )
    }

    fun unregister(){
        connectivityManager.unregisterNetworkCallback(
            networkListener
        )
    }
}