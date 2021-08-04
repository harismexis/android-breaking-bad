package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.video.VideosLocal
import com.harismexis.breakingbad.core.result.VideosResult
import com.harismexis.breakingbad.framework.util.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoPickerViewModel @Inject constructor(
    val videosLocal: VideosLocal
) : ViewModel() {

    private val TAG = VideoPickerViewModel::class.qualifiedName

    private val mVideos = MutableLiveData<VideosResult>()
    val videos: LiveData<VideosResult>
        get() = mVideos

//    private val mShowErrorMessage = MutableLiveData<Event<String>>()
//    val showErrorMessage: LiveData<Event<String>>
//        get() = mShowErrorMessage

    fun updateVideos() {
        viewModelScope.launch {
            try {
                val items = videosLocal.getVideos()
                mVideos.value = VideosResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mVideos.value = VideosResult.Error(e)
                // mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}