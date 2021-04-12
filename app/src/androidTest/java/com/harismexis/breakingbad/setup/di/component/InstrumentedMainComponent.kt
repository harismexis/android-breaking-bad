package com.harismexis.breakingbad.setup.di.component

import com.harismexis.breakingbad.framework.di.ui.FragmentBindingsModule
import com.harismexis.breakingbad.setup.application.InstrumentedMainApplication
import com.harismexis.breakingbad.setup.di.module.InstrumentedApplicationModule
import com.harismexis.breakingbad.setup.viewmodel.factory.InstrumentedViewModelModule
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
        InstrumentedViewModelModule::class,
        InstrumentedApplicationModule::class
    ]
)
interface InstrumentedMainComponent : AndroidInjector<InstrumentedMainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedMainApplication)
                : InstrumentedMainComponent
    }

}