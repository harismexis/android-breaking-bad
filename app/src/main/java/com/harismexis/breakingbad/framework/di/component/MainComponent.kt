package com.harismexis.breakingbad.framework.di.component

import com.harismexis.breakingbad.framework.application.MainApplication
import com.harismexis.breakingbad.framework.di.module.BreakingBadApiModule
import com.harismexis.breakingbad.framework.di.module.FragmentBindingsModule
import com.harismexis.breakingbad.framework.di.module.RepositoryModule
import com.harismexis.breakingbad.framework.di.module.RoomModule
import com.harismexis.breakingbad.presentation.vmfactory.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBindingsModule::class,
        ViewModelModule::class,
        BreakingBadApiModule::class,
        RepositoryModule::class,
        RoomModule::class
    ]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication): MainComponent
    }

}