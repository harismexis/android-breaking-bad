package com.example.jsonfeed.instrumentedsetup.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.jsonfeed.instrumentedsetup.mockproviders.provideMockVmMap

import dagger.Binds
import dagger.MapKey
import dagger.Module

import javax.inject.Inject
import javax.inject.Singleton

import kotlin.reflect.KClass

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        provideMockVmMap()[modelClass]?.get() as T
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
            : ViewModelProvider.Factory
}