package com.harismexis.breakingbad.setup.viewmodel

import androidx.lifecycle.MutableLiveData
import com.harismexis.breakingbad.model.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import io.mockk.mockk

object MockHomeVmProvider {

    val mockHomeViewModel: HomeViewModel = mockk(relaxed = true)
    var fakeActorsResult = MutableLiveData<ActorsResult>()

}