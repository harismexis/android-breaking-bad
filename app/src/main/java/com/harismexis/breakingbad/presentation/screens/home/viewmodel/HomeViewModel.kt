package com.harismexis.breakingbad.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.framework.event.Event
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.home.interactors.HomeInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mActorsResult = MutableLiveData<ActorsResult>()
    val actorsResult: LiveData<ActorsResult>
        get() = mActorsResult

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    private var searchQuery: String? = null

    fun fetchInitialActors() {
        if (connectivity.isOnline()) {
            fetchRemoteActors(searchQuery)
        } else {
            fetchLocalActors()
        }
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        fetchRemoteActors(query)
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) fetchRemoteActors(searchQuery)
    }

    private fun fetchRemoteActors(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetRemoteActors(name)
                mActorsResult.value = ActorsResult.ActorsSuccess(items)
                interactors.irrStoreActors(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mActorsResult.value = ActorsResult.ActorsError(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalActors() {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetLocalActors()
                mActorsResult.value = ActorsResult.ActorsSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mActorsResult.value = ActorsResult.ActorsError(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}