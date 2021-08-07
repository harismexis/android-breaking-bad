package com.harismexis.breakingbad.config.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.breakingbad.mocks.createMockVmMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        createMockVmMap()[modelClass]?.get() as T
}