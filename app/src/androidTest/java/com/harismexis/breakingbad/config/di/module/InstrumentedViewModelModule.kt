package com.harismexis.breakingbad.config.di.module

import androidx.lifecycle.ViewModelProvider
import com.harismexis.breakingbad.config.vmfactory.InstrumentedHomeViewModelFactory
import com.harismexis.breakingbad.config.vmfactory.InstrumentedViewModelFactory
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.breakingbad.presentation.vmfactory.assisted.ViewModelAssistedFactory
import dagger.Binds
import dagger.Module

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
            : ViewModelProvider.Factory

    @Binds
    internal abstract fun bindHomeViewModelFactory(factory: InstrumentedHomeViewModelFactory)
            : ViewModelAssistedFactory<HomeViewModel>
}

