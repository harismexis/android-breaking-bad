package com.harismexis.breakingbad.framework.di.interactor

import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActor
import com.harismexis.breakingbad.presentation.screens.actordetail.interactors.ActorDetailInteractors
import dagger.Module
import dagger.Provides

@Module
class ActorDetailInteractorModule {

    @Provides
    fun provideActorDetailInteractors(
        irrGetLocalActor: IrrGetLocalActor
    ): ActorDetailInteractors {
        return ActorDetailInteractors(irrGetLocalActor)
    }

}