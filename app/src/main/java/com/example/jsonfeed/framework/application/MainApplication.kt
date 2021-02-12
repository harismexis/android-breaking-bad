package com.example.jsonfeed.framework.application

import com.example.jsonfeed.framework.di.DaggerMainComponent
import com.example.jsonfeed.framework.di.MainComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication

import javax.inject.Inject

class MainApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    private lateinit var component: MainComponent

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerMainComponent.factory().create(this)
        component.inject(this)
        return component
    }

}