package com.example.breakingbad.framework.di.interactor

import com.example.breakingbad.interactors.actor.IrrGetLocalActors
import com.example.breakingbad.interactors.actor.IrrGetRemoteActors
import com.example.breakingbad.interactors.actor.IrrStoreActors
import com.example.breakingbad.presentation.screens.home.interactors.HomeInteractors
import dagger.Module
import dagger.Provides

@Module
class HomeInteractorModule {

    @Provides
    fun provideHomeInteractors(
        irrGetLocalActors: IrrGetLocalActors,
        irrGetRemoteActors: IrrGetRemoteActors,
        irrStoreActors: IrrStoreActors
    ): HomeInteractors {
        return HomeInteractors(
            irrGetLocalActors,
            irrGetRemoteActors,
            irrStoreActors
        )
    }

}