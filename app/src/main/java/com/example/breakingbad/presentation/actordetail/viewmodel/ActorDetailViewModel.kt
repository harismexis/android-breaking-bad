package com.example.breakingbad.presentation.actordetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.presentation.interactors.ActorDetailInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorDetailViewModel @Inject constructor(
    private val interactors: ActorDetailInteractors
) : ViewModel() {

    private val tag = ActorDetailViewModel::class.qualifiedName

    private val mModel = MutableLiveData<Actor>()
    val model: LiveData<Actor>
        get() = mModel

    fun retrieveItemById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = interactors.irrGetLocalItem(itemId)
                item?.let {
                    mModel.value = it
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
            }
        }
    }

}
