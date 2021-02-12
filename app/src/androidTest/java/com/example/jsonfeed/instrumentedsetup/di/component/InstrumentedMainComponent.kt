package com.example.jsonfeed.instrumentedsetup.di.component

import com.example.jsonfeed.instrumentedsetup.application.InstrumentedMainApplication
import com.example.jsonfeed.instrumentedsetup.di.module.InstrumentedApplicationModule
import com.example.jsonfeed.instrumentedsetup.di.module.InstrumentedInteractorModule
import com.example.jsonfeed.instrumentedsetup.factory.InstrumentedViewModelModule
import com.example.jsonfeed.framework.di.ActivityBindingsModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingsModule::class,
        InstrumentedViewModelModule::class,
        InstrumentedApplicationModule::class,
        InstrumentedInteractorModule::class
    ]
)
interface InstrumentedMainComponent : AndroidInjector<InstrumentedMainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedMainApplication)
                : InstrumentedMainComponent
    }

}