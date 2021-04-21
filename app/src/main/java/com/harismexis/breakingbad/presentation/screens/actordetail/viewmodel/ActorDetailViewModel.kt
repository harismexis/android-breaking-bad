package com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.datamodel.data.LocalRepository
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.presentation.result.ActorDetailResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorDetailViewModel @Inject constructor(
    private val localRepo: LocalRepository,
) : ViewModel() {

    private val tag = ActorDetailViewModel::class.qualifiedName

    private val mActorDetailResult = MutableLiveData<ActorDetailResult>()
    val actorDetailResult: LiveData<ActorDetailResult>
        get() = mActorDetailResult

    fun retrieveActorById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = localRepo.getActor(itemId)
                item?.let {
                    mActorDetailResult.value = ActorDetailResult.ActorSuccess(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mActorDetailResult.value = ActorDetailResult.ActorError(e.getErrorMessage())
            }
        }
    }

}
