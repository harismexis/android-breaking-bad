package com.harismexis.breakingbad.presentation.screens.episodes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.datamodel.repo.EpisodeLocalRepo
import com.harismexis.breakingbad.datamodel.repo.EpisodeRemoteRepo
import com.harismexis.breakingbad.framework.event.Event
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.EpisodesResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val episodesRemote: EpisodeRemoteRepo,
    private val episodesLocal: EpisodeLocalRepo,
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
                val items = episodesRemote.getEpisodes(seriesName)
                mEpisodes.value = EpisodesResult.Success(items)
                episodesLocal.insertEpisodes(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mEpisodes.value = EpisodesResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalEpisodes() {
        viewModelScope.launch {
            try {
                val items = episodesLocal.getEpisodes()
                mEpisodes.value = EpisodesResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mEpisodes.value = EpisodesResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}