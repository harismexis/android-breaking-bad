package com.harismexis.breakingbad.framework.application

import com.harismexis.breakingbad.framework.di.DaggerMainComponent
import com.harismexis.breakingbad.framework.di.MainComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

class MainApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    private lateinit var component: MainComponent

    val appScope = CoroutineScope(SupervisorJob())

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerMainComponent.factory().create(this)
        component.inject(this)
        return component
    }

}