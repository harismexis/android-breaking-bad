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

    fun searchActors(query: String?) {
        searchQuery = query
        viewModelScope.launch {
            searchActors()
        }
    }

    fun updateActors() {
        viewModelScope.launch {
            updateActorsFromRemote()
            searchActors()
        }
    }

    private suspend fun updateActorsFromRemote() {
        try {
            val items = actorRemote.getActors()
            actorLocal.save(items)
        } catch (e: Exception) {
            Log.d(TAG, e.getErrorMessage())
            mShowErrorMessage.value = Event(e.getErrorMessage())
        }
    }

    private suspend fun searchActors() {
        try {
            val actors =
                if (!searchQuery.isNullOrBlank()) actorLocal.searchActors(searchQuery)
                else actorLocal.getActors()
            mActorsResult.value = ActorsResult.Success(actors)
        } catch (e: Exception) {
            Log.d(TAG, e.getErrorMessage())
            mActorsResult.value = ActorsResult.Error(e)
            mShowErrorMessage.value = Event(e.getErrorMessage())
        }
    }
}