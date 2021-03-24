package com.harismexis.breakingbad.setup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel

import io.mockk.mockk

object MockActorDetailViewModel {

    private var mockActorDetailViewModel: ActorDetailViewModel = mockk(relaxed = true)

    var mModel = MutableLiveData<Actor>()
    val model: LiveData<Actor>
        get() = mModel

    fun getMockActorDetailViewModel(): ActorDetailViewModel {
        return mockActorDetailViewModel
    }

}