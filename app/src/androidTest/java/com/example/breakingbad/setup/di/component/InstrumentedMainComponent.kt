package com.example.breakingbad.setup.di.component

import com.example.breakingbad.setup.application.InstrumentedMainApplication
import com.example.breakingbad.setup.di.module.InstrumentedApplicationModule
import com.example.breakingbad.setup.factory.InstrumentedViewModelModule
import com.example.breakingbad.framework.di.ui.ActivityBindingsModule

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