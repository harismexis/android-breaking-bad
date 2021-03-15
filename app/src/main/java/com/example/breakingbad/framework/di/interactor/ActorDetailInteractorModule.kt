package com.example.breakingbad.framework.di.interactor

import com.example.breakingbad.interactors.actor.IrrGetLocalActor
import com.example.breakingbad.presentation.screens.actordetail.interactors.ActorDetailInteractors
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