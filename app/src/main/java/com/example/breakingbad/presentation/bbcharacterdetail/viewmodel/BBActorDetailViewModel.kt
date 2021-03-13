package com.example.breakingbad.presentation.bbcharacterdetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.presentation.interactors.BBActorDetailInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class BBActorDetailViewModel @Inject constructor(
    private val interactors: BBActorDetailInteractors
) : ViewModel() {

    private val tag = BBActorDetailViewModel::class.qualifiedName

    private val mModel = MutableLiveData<BBActor>()
    val model: LiveData<BBActor>
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
