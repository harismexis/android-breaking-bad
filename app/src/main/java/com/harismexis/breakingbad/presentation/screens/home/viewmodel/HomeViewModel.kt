package com.harismexis.breakingbad.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.actor.ActorsLocal
import com.harismexis.breakingbad.core.repository.actor.ActorsRemote
import com.harismexis.breakingbad.core.result.ActorsResult
import com.harismexis.breakingbad.framework.util.event.Event
import com.harismexis.breakingbad.framework.util.extensions.getErrorMessage
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

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        updateActors()
    }

    fun updateActors() {
        fetchActors(searchQuery)
    }

    private fun fetchActors(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = actorRemote.getActors(name)
                mActorsResult.value = ActorsResult.Success(items)
                actorLocal.save(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mShowErrorMessage.value = Event(e.getErrorMessage())
                fetchCachedActors()
            }
        }
    }

    private suspend fun fetchCachedActors() {
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