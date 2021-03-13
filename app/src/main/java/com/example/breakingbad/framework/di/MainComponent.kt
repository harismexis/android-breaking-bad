package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.application.MainApplication
import com.example.breakingbad.framework.viewmodel.factory.ViewModelModule
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
        FragmentBindingsModule::class,
        ViewModelModule::class,
        ApplicationModule::class,
        ActorInteractorModule::class,
        QuoteInteractorModule::class,
        BreakingBadApiModule::class]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication): MainComponent
    }

}