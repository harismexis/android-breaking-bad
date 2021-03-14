package com.example.breakingbad.presentation.screens.episodes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.Episode
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.presentation.screens.episodes.interactors.EpisodeInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val interactors: EpisodeInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = EpisodesViewModel::class.qualifiedName

    private val mModels = MutableLiveData<List<Episode>>()
    val models: LiveData<List<Episode>>
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
                val items = interactors.irrGetRemoteEpisodes.invoke()
                mModels.value = items
                interactors.irrStoreEpisodes.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.irrGetLocalEpisodes.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}