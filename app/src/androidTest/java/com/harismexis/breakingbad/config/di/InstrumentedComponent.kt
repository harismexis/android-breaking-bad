package com.harismexis.breakingbad.config.di

import com.harismexis.breakingbad.config.application.InstrumentedApplication
import com.harismexis.breakingbad.config.vmfactory.InstrumentedViewModelModule
import com.harismexis.breakingbad.framework.di.FragmentBindingsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBindingsModule::class,
        InstrumentedViewModelModule::class
    ]
)
interface InstrumentedComponent : AndroidInjector<InstrumentedApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedApplication)
                : InstrumentedComponent
    }

}