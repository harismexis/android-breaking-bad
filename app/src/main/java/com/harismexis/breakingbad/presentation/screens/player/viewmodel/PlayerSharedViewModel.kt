package com.harismexis.breakingbad.presentation.screens.player.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.video.VideosLocal
import com.harismexis.breakingbad.core.result.VideosResult
import com.harismexis.breakingbad.core.util.getErrorMessage
import com.harismexis.breakingbad.framework.util.event.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerSharedViewModel @Inject constructor(
    private val videosLocal: VideosLocal
) : ViewModel() {

    companion object {
        val TAG = PlayerSharedViewModel::class.qualifiedName
    }

    private val mVideos = MutableLiveData<VideosResult>()
    val videos: LiveData<VideosResult>
        get() = mVideos

    private val mLoadVideo = MutableLiveData<Event<String>>()
    val loadVideo: LiveData<Event<String>>
        get() = mLoadVideo

    fun updateVideos() {
        viewModelScope.launch {
            try {
                val items = videosLocal.getVideos()
                mVideos.value = VideosResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mVideos.value = VideosResult.Error(e)
            }
        }
    }

    fun loadVideo(videoId: String) {
        mLoadVideo.value = Event(videoId)
    }

}