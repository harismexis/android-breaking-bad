package com.harismexis.breakingbad.presentation.screens.deaths.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.death.DeathsLocal
import com.harismexis.breakingbad.core.repository.death.DeathsRemote
import com.harismexis.breakingbad.core.result.DeathsResult
import com.harismexis.breakingbad.framework.util.event.Event
import com.harismexis.breakingbad.framework.util.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeathsViewModel @Inject constructor(
    private val deathRemote: DeathsRemote,
    private val deathLocal: DeathsLocal
) : ViewModel() {

    private val TAG = DeathsViewModel::class.qualifiedName

    private val mDeaths = MutableLiveData<DeathsResult>()
    val deaths: LiveData<DeathsResult>
        get() = mDeaths

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage: LiveData<Event<String>>
        get() = mShowErrorMessage

    fun updateDeaths() {
        fetchDeaths()
    }

    private fun fetchDeaths() {
        viewModelScope.launch {
            try {
                val items = deathRemote.getDeaths()
                mDeaths.value = DeathsResult.Success(items)
                deathLocal.insertDeaths(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mShowErrorMessage.value = Event(e.getErrorMessage())
                fetchLocalDeaths()
            }
        }
    }

    private suspend fun fetchLocalDeaths() {
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