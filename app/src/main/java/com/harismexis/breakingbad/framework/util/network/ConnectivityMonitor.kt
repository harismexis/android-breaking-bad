package com.harismexis.breakingbad.framework.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import com.harismexis.breakingbad.framework.util.network.ConnectivityRequestProvider
import com.harismexis.breakingbad.framework.util.network.ConnectivityState
import com.jakewharton.rxrelay2.PublishRelay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityMonitor @Inject constructor
    (
    var appContext: Context,
    var requestProvider: ConnectivityRequestProvider
) : NetworkCallback() {

    private val connectivityUpdates: PublishRelay<ConnectivityState> =
        PublishRelay.create()

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        connectivityUpdates.accept(ConnectivityState.CONNECTED)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        connectivityUpdates.accept(ConnectivityState.DISCONNECTED)
    }

    fun startMonitor() {
        val connectivityManager = appContext.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(requestProvider.provideNetworkRequest(), this)
    }

    fun stopMonitor() {
        val connectivityManager = appContext.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(this)
    }

    fun getConnectivityUpdates(): PublishRelay<ConnectivityState> {
        return connectivityUpdates
    }

}