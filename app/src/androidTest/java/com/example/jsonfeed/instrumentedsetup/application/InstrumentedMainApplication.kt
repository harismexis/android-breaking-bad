package com.example.jsonfeed.instrumentedsetup.application

import com.example.jsonfeed.instrumentedsetup.di.component.DaggerInstrumentedMainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class InstrumentedMainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val mainComponent = DaggerInstrumentedMainComponent.factory().create(this)
        mainComponent.inject(this)
        return mainComponent
    }
}