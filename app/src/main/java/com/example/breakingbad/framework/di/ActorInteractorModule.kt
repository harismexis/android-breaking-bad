package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.data.BreakingBadRemoteRepository
import com.example.breakingbad.presentation.screens.actordetail.interactors.ActorDetailInteractors
import com.example.breakingbad.presentation.screens.home.interactors.HomeInteractors
import com.example.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.example.breakingbad.interactors.actor.IrrGetLocalActor
import com.example.breakingbad.interactors.actor.IrrGetLocalActors
import com.example.breakingbad.interactors.actor.IrrGetRemoteActors
import com.example.breakingbad.interactors.actor.IrrStoreActors
import dagger.Module
import dagger.Provides

@Module
class ActorInteractorModule {

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

    @Provides
    fun provideActorDetailInteractors(
        irrGetLocalActor: IrrGetLocalActor
    ): ActorDetailInteractors {
        return ActorDetailInteractors(irrGetLocalActor)
    }

    @Provides
    fun provideIrrGetRemoteActors(
        dataSource: BreakingBadRemoteDataSource
    ): IrrGetRemoteActors {
        return IrrGetRemoteActors(BreakingBadRemoteRepository(dataSource))
    }

    @Provides
    fun provideIrrGetLocalActors(
        dataSource: BreakingBadLocalDataSource
    ): IrrGetLocalActors {
        return IrrGetLocalActors(BreakingBadLocalRepository(dataSource))
    }

    @Provides
    fun provideIrrGetLocalActor(
        dataSource: BreakingBadLocalDataSource
    ): IrrGetLocalActor {
        return IrrGetLocalActor(BreakingBadLocalRepository(dataSource))
    }

    @Provides
    fun provideIrrInsertActors(
        dataSource: BreakingBadLocalDataSource
    ): IrrStoreActors {
        return IrrStoreActors(BreakingBadLocalRepository(dataSource))
    }

}