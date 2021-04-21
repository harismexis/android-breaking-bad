package com.harismexis.breakingbad.presentation.screens.episodes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.datamodel.data.LocalRepository
import com.harismexis.breakingbad.datamodel.data.RemoteRepository
import com.harismexis.breakingbad.framework.event.Event
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.EpisodesResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val remoteRepo: RemoteRepository,
    private val localRepo: LocalRepository,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = EpisodesViewModel::class.qualifiedName

    private val mEpisodes = MutableLiveData<EpisodesResult>()
    val episodes: LiveData<EpisodesResult>
        get() = mEpisodes

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage: LiveData<Event<String>>
        get() = mShowErrorMessage

    private var seriesName: String? = null

    fun fetchEpisodes(seriesName: String?) {
        this.seriesName = seriesName
        if (connectivity.isOnline()) {
            fetchRemoteEpisodes(seriesName)
        } else {
            fetchLocalEpisodes()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteEpisodes(this.seriesName)
        }
    }

    private fun fetchRemoteEpisodes(seriesName: String?) {
        viewModelScope.launch {
            try {
                val items = remoteRepo.getEpisodes(seriesName)
                mEpisodes.value = EpisodesResult.EpisodesSuccess(items)
                localRepo.insertEpisodes(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mEpisodes.value = EpisodesResult.EpisodesError(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalEpisodes() {
        viewModelScope.launch {
            try {
                val items = localRepo.getEpisodes()
                mEpisodes.value = EpisodesResult.EpisodesSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mEpisodes.value = EpisodesResult.EpisodesError(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}