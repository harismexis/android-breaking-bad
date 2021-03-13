package com.example.breakingbad.setup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.presentation.home.viewmodel.HomeViewModel

import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeViewModel: HomeViewModel = mockk(relaxed = true)

    var mModels = MutableLiveData<List<BBActor>>()
    val models: LiveData<List<BBActor>>
        get() = mModels

    fun provideMockHomeVm(): HomeViewModel {
        return mockHomeViewModel
    }


}