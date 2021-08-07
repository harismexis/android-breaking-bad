package com.harismexis.breakingbad.config.vmfactory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.breakingbad.mocks.createMockVmMap
import com.harismexis.breakingbad.mocks.mockHomeViewModel
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.breakingbad.presentation.vmfactory.assisted.ViewModelAssistedFactory
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        createMockVmMap()[modelClass]?.get() as T
}

@Singleton
class InstrumentedHomeViewModelFactory @Inject constructor() :
    ViewModelAssistedFactory<HomeViewModel> {
    override fun create(handle: SavedStateHandle): HomeViewModel {
        return mockHomeViewModel
    }
}

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
            : ViewModelProvider.Factory

    @Binds
    internal abstract fun bindHomeViewModelFactory(factory: InstrumentedHomeViewModelFactory)
            : ViewModelAssistedFactory<HomeViewModel>
}

