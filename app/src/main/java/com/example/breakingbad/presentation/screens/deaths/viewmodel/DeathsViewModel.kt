package com.example.breakingbad.presentation.screens.deaths.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.Death
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.presentation.screens.deaths.interactors.DeathInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeathsViewModel @Inject constructor(
    private val interactors: DeathInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = DeathsViewModel::class.qualifiedName

    private val mModels = MutableLiveData<List<Death>>()
    val models: LiveData<List<Death>>
        get() = mModels

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
                mModels.value = items
                interactors.irrStoreDeaths.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.irrGetLocalDeaths.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}