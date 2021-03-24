package com.harismexis.breakingbad.framework.util.network

import android.net.NetworkCapabilities
import android.net.NetworkRequest
import javax.inject.Inject

class ConnectivityRequestProvider @Inject constructor() {

    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

}

