package com.harismexis.breakingbad.config.di.component

import com.harismexis.breakingbad.config.application.InstrumentedApplication
import com.harismexis.breakingbad.config.di.module.InstrumentedViewModelModule
import com.harismexis.breakingbad.framework.di.module.FragmentBindingsModule
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