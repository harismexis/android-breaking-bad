package com.example.breakingbad.presentation.screens.deaths.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.presentation.result.DeathsResult
import com.example.breakingbad.presentation.screens.deaths.interactors.DeathInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeathsViewModel @Inject constructor(
    private val interactors: DeathInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = DeathsViewModel::class.qualifiedName

    private val mDeaths = MutableLiveData<DeathsResult>()
    val deaths: LiveData<DeathsResult>
        get() = mDeaths

    fun bind() {
        if (connectivity.isOnline()) {
            fetchRemoteItems()
        } else {
            fetchLocalItems()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteItems()
        }
    }

    private fun fetchRemoteItems() {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetRemoteDeaths.invoke()
                mDeaths.value = DeathsResult.DeathsSuccess(items)
                interactors.irrStoreDeaths.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mDeaths.value = DeathsResult.DeathsError(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetLocalDeaths.invoke()
                mDeaths.value = DeathsResult.DeathsSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mDeaths.value = DeathsResult.DeathsError(e.getErrorMessage())
            }
        }
    }

}