package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.video.VideosLocal
import com.harismexis.breakingbad.core.result.VideosResult
import com.harismexis.breakingbad.core.util.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoPickerViewModel @Inject constructor(
    private val videosLocal: VideosLocal
) : ViewModel() {

    companion object {
        val TAG = VideoPickerViewModel::class.qualifiedName
    }

    private val mVideos = MutableLiveData<VideosResult>()
    val videos: LiveData<VideosResult>
        get() = mVideos

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

}