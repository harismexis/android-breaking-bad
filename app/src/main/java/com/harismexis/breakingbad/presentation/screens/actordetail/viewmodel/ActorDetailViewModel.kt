package com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.framework.data.database.repository.ActorsLocalRepository
import com.harismexis.breakingbad.core.util.getErrorMessage
import com.harismexis.breakingbad.core.result.ActorDetailResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorDetailViewModel @Inject constructor(
    private val actorLocal: ActorsLocalRepository,
) : ViewModel() {

    private val tag = ActorDetailViewModel::class.qualifiedName

    private val mActorDetailResult = MutableLiveData<ActorDetailResult>()
    val actorDetailResult: LiveData<ActorDetailResult>
        get() = mActorDetailResult

    fun retrieveActorById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = actorLocal.getActor(itemId)
                item?.let {
                    mActorDetailResult.value = ActorDetailResult.Success(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mActorDetailResult.value = ActorDetailResult.Error(e.getErrorMessage())
            }
        }
    }

}
