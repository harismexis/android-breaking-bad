package com.example.jsonfeed.presentation.home.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.extensions.*
import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.framework.util.functional.Action1
import com.example.jsonfeed.framework.util.network.ConnectivityMonitor

import kotlinx.coroutines.launch

import javax.inject.Inject

class HomeVm @Inject constructor(
    val interactors: Interactors,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeVm::class.qualifiedName

    private val mModels = MutableLiveData<List<Item>>()
    val models: LiveData<List<Item>>
        get() = mModels

    fun bind() {
        if (connectivity.isOnline()) {
            fetchRemoteFeed()
        } else {
            fetchLocalFeed()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteFeed()
        }
    }

    private fun fetchRemoteFeed() {
        viewModelScope.launch {
            try {
                val items = interactors.getRemoteItems.invoke()
                items?.let {
                    mModels.value = it
                    interactors.storeItems.invoke(items)
                }
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalFeed() {
        viewModelScope.launch {
            try {
                val items = interactors.getLocalItems.invoke()
                items?.let {
                    mModels.value = it
                }
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}