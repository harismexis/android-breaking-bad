package com.harismexis.breakingbad.config.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.breakingbad.mocks.createMockVmMap
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        createMockVmMap()[modelClass]?.get() as T
}

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
            : ViewModelProvider.Factory
}