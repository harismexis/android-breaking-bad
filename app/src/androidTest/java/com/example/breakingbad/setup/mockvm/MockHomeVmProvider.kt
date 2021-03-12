package com.example.breakingbad.setup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.home.viewmodel.HomeViewModel

import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeViewModel: HomeViewModel = mockk(relaxed = true)

    var mModels = MutableLiveData<List<BBCharacter>>()
    val models: LiveData<List<BBCharacter>>
        get() = mModels

    fun provideMockHomeVm(): HomeViewModel {
        return mockHomeViewModel
    }


}