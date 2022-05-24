package com.coding.flickertask.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class NetworkStatus(context: Context) : LiveData<Boolean>() {
    private val networkManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallbacks = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }
    }

    private fun checkInternetStatus(){
        val network = networkManager.activeNetwork
        if(network == null){
            postValue(false)
        }

        /**
         * Checking network internet capabilities
         * where the connection has internet or not
         * Also check network capabilities via the callbacks
         */
        val requestBuilder =NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) // also for sdk version 23 or above
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        }.build()

        networkManager.registerNetworkCallback(requestBuilder,networkCallbacks)
    }

    override fun onActive() {
        super.onActive()
        checkInternetStatus()
    }

    override fun onInactive() {
        super.onInactive()
        networkManager.unregisterNetworkCallback(networkCallbacks)
    }
}