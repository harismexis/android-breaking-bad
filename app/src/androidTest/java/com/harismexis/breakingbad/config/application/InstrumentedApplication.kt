package com.harismexis.breakingbad.config.application

import com.harismexis.breakingbad.config.di.DaggerInstrumentedComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class InstrumentedApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val mainComponent = DaggerInstrumentedComponent.factory().create(this)
        mainComponent.inject(this)
        return mainComponent
    }

}