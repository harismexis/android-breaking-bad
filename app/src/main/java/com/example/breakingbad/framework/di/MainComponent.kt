package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.application.MainApplication
import com.example.breakingbad.framework.di.api.BreakingBadApiModule
import com.example.breakingbad.framework.di.application.ApplicationModule
import com.example.breakingbad.framework.di.interactor.*
import com.example.breakingbad.framework.di.ui.ActivityBindingsModule
import com.example.breakingbad.framework.di.ui.FragmentBindingsModule
import com.example.breakingbad.framework.viewmodelfactory.ViewModelModule
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
        BreakingBadApiModule::class,
        ActorInteractorModule::class,
        HomeInteractorModule::class,
        ActorDetailInteractorModule::class,
        QuoteInteractorModule::class,
        DeathInteractorModule::class,
        EpisodeInteractorModule::class,
    ]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication): MainComponent
    }

}