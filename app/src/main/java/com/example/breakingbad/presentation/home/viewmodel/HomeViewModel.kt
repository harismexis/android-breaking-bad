package com.example.breakingbad.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mModels = MutableLiveData<List<BBCharacter>>()
    val models: LiveData<List<BBCharacter>>
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
                val items = interactors.interGetRemoteBBCharacters.invoke()
                mModels.value = items
                interactors.interStoreBBCharacters.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.interGetLocalBBCharacters.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}