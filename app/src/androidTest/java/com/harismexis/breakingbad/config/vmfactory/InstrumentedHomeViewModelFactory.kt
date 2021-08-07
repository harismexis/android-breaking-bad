package com.harismexis.breakingbad.config.vmfactory

import androidx.lifecycle.SavedStateHandle
import com.harismexis.breakingbad.mocks.mockHomeViewModel
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.breakingbad.presentation.vmfactory.assisted.ViewModelAssistedFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstrumentedHomeViewModelFactory @Inject constructor() :
    ViewModelAssistedFactory<HomeViewModel> {
    override fun create(handle: SavedStateHandle): HomeViewModel {
        return mockHomeViewModel
    }
}
