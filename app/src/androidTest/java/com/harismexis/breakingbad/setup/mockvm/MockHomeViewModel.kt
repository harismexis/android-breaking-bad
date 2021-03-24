package com.harismexis.breakingbad.setup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel

import io.mockk.mockk

object MockHomeViewModel {

    private var mockHomeViewModel: HomeViewModel = mockk(relaxed = true)

    var mModels = MutableLiveData<List<Actor>>()
    val models: LiveData<List<Actor>>
        get() = mModels

    fun getMockHomeViewModel(): HomeViewModel {
        return mockHomeViewModel
    }


}