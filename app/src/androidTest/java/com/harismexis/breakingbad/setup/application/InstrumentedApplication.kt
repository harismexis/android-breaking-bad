package com.harismexis.breakingbad.setup.application

import com.harismexis.breakingbad.setup.di.component.DaggerInstrumentedTestComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class InstrumentedApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val mainComponent = DaggerInstrumentedTestComponent.factory().create(this)
        mainComponent.inject(this)
        return mainComponent
    }

}