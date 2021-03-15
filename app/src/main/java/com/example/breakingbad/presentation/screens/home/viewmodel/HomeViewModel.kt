package com.example.breakingbad.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.presentation.screens.home.interactors.HomeInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mModels = MutableLiveData<List<Actor>>()
    val models: LiveData<List<Actor>>
        get() = mModels

    var searchQuery: String? = null
        set(value) {
            field = value
            fetchRemoteActors(value)
        }

    fun bind() {
        if (connectivity.isOnline()) {
            fetchRemoteActors(searchQuery)
        } else {
            fetchLocalActors()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) fetchRemoteActors(searchQuery)
    }

    private fun fetchRemoteActors(name: String?) {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetRemoteActors.invoke(name)
                mModels.value = items
                interactors.irrStoreActors.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalActors() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.irrGetLocalActors.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}