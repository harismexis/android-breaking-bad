package com.harismexis.breakingbad.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.framework.event.Event
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.model.repository.ActorsLocal
import com.harismexis.breakingbad.model.repository.ActorsRemote
import com.harismexis.breakingbad.presentation.result.ActorsResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val actorRemote: ActorsRemote,
    private val actorLocal: ActorsLocal
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mActorsResult = MutableLiveData<ActorsResult>()
    val actorsResult: LiveData<ActorsResult>
        get() = mActorsResult

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage: LiveData<Event<String>>
        get() = mShowErrorMessage

    private var searchQuery: String? = null

    fun fetchActors() {
        fetchRemoteActors(searchQuery)
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        fetchRemoteActors(query)
    }

    private fun fetchRemoteActors(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = actorRemote.getActors(name)
                mActorsResult.value = ActorsResult.Success(items)
                actorLocal.updateActors(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mActorsResult.value = ActorsResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
                fetchLocalActors()
            }
        }
    }

    private fun fetchLocalActors() {
        viewModelScope.launch {
            try {
                val items = actorLocal.getActors()
                mActorsResult.value = ActorsResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mActorsResult.value = ActorsResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}