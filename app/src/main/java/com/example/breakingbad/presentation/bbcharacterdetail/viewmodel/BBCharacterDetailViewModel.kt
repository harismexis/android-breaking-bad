package com.example.breakingbad.presentation.bbcharacterdetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.interactors.BBCharacterDetailInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class BBCharacterDetailViewModel @Inject constructor(
    private val interactors: BBCharacterDetailInteractors
) : ViewModel() {

    private val tag = BBCharacterDetailViewModel::class.qualifiedName

    private val mModel = MutableLiveData<BBCharacter>()
    val model: LiveData<BBCharacter>
        get() = mModel

    fun retrieveItemById(itemId: String) {
        viewModelScope.launch {
            try {
                val item = interactors.interGetLocalItem(itemId)
                item?.let {
                    mModel.value = it
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
            }
        }
    }

}
