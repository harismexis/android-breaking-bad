package com.harismexis.breakingbad.presentation.screens.deaths.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.model.repository.DeathsLocalRepository
import com.harismexis.breakingbad.model.repository.DeathsRemoteRepository
import com.harismexis.breakingbad.framework.event.Event
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.DeathsResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeathsViewModel @Inject constructor(
    private val deathRemote: DeathsRemoteRepository,
    private val deathLocal: DeathsLocalRepository,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = DeathsViewModel::class.qualifiedName

    private val mDeaths = MutableLiveData<DeathsResult>()
    val deaths: LiveData<DeathsResult>
        get() = mDeaths

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    fun fetchDeaths() {
        if (connectivity.isOnline()) {
            fetchRemoteDeaths()
        } else {
            fetchLocalDeaths()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteDeaths()
        }
    }

    private fun fetchRemoteDeaths() {
        viewModelScope.launch {
            try {
                val items = deathRemote.getDeaths()
                mDeaths.value = DeathsResult.Success(items)
                deathLocal.insertDeaths(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mDeaths.value = DeathsResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalDeaths() {
        viewModelScope.launch {
            try {
                val items = deathLocal.getDeaths()
                mDeaths.value = DeathsResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mDeaths.value = DeathsResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}