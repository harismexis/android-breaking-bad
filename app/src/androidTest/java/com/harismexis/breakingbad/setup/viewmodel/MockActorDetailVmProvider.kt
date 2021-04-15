package com.harismexis.breakingbad.setup.viewmodel

import androidx.lifecycle.MutableLiveData
import com.harismexis.breakingbad.presentation.result.ActorDetailResult
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import io.mockk.mockk

object MockActorDetailVmProvider {

    val mockActorDetailViewModel: ActorDetailViewModel = mockk(relaxed = true)
    var fakeActorDetailResult = MutableLiveData<ActorDetailResult>()

}