package com.harismexis.breakingbad.presentation.screens.episodes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.EpisodesResult
import com.harismexis.breakingbad.presentation.screens.episodes.interactors.EpisodeInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val interactors: EpisodeInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = EpisodesViewModel::class.qualifiedName

    private val mEpisodes = MutableLiveData<EpisodesResult>()
    val episodes: LiveData<EpisodesResult>
        get() = mEpisodes

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
                mEpisodes.value = EpisodesResult.EpisodesSuccess(items)
                interactors.irrStoreEpisodes.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mEpisodes.value = EpisodesResult.EpisodesError(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetLocalEpisodes.invoke()
                mEpisodes.value = EpisodesResult.EpisodesSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mEpisodes.value = EpisodesResult.EpisodesError(e.getErrorMessage())
            }
        }
    }

}