package com.harismexis.breakingbad.framework.di.interactor

import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActors
import com.harismexis.breakingbad.interactors.actor.IrrGetRemoteActors
import com.harismexis.breakingbad.interactors.actor.IrrStoreActors
import com.harismexis.breakingbad.presentation.screens.home.interactors.HomeInteractors
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