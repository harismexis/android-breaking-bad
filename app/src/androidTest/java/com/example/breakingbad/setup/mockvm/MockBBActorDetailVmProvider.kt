package com.example.breakingbad.setup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.presentation.bbcharacterdetail.viewmodel.BBActorDetailViewModel

import io.mockk.mockk

object MockBBActorDetailVmProvider {

    private var mockBBActorDetailVm: BBActorDetailViewModel = mockk(relaxed = true)

    var mModel = MutableLiveData<BBActor>()
    val model: LiveData<BBActor>
        get() = mModel

    fun provideMockBBActorDetailVm(): BBActorDetailViewModel {
        return mockBBActorDetailVm
    }

}