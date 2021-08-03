package com.harismexis.breakingbad.presentation.screens.episodes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.episode.EpisodesLocal
import com.harismexis.breakingbad.core.repository.episode.EpisodesRemote
import com.harismexis.breakingbad.core.result.EpisodesResult
import com.harismexis.breakingbad.framework.util.event.Event
import com.harismexis.breakingbad.framework.util.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val episodesRemote: EpisodesRemote,
    private val episodesLocal: EpisodesLocal
) : ViewModel() {

    private val TAG = EpisodesViewModel::class.qualifiedName

    private val mEpisodes = MutableLiveData<EpisodesResult>()
    val episodes: LiveData<EpisodesResult>
        get() = mEpisodes

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage: LiveData<Event<String>>
        get() = mShowErrorMessage

    var seriesName: String? = null

    fun updateEpisodes() {
        fetchEpisodes(seriesName)
    }

    private fun fetchEpisodes(seriesName: String?) {
        viewModelScope.launch {
            try {
                val items = episodesRemote.getEpisodes(seriesName)
                mEpisodes.value = EpisodesResult.Success(items)
                episodesLocal.save(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mShowErrorMessage.value = Event(e.getErrorMessage())
                fetchLocalEpisodes()
            }
        }
    }

    private suspend fun fetchLocalEpisodes() {
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