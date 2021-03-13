package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.database.BreakingBadLocalDataSource
import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.data.BreakingBadRemoteRepository
import com.example.breakingbad.presentation.interactors.ActorDetailInteractors
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.example.breakingbad.interactors.*
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideHomeInteractors(
        irrGetLocalActors: IrrGetLocalActors,
        irrGetRemoteActors: IrrGetRemoteActors,
        irrGetRemoteActorsByName: IrrGetRemoteActorsByName,
        irrStoreActors: IrrStoreActors
    ): HomeInteractors {
        return HomeInteractors(
            irrGetLocalActors,
            irrGetRemoteActors,
            irrGetRemoteActorsByName,
            irrStoreActors
        )
    }

    @Provides
    fun provideBBActorDetailInteractors(
        irrGetLocalActor: IrrGetLocalActor
    ): ActorDetailInteractors {
        return ActorDetailInteractors(irrGetLocalActor)
    }

    @Provides
    fun provideIrrGetRemoteBBActors(
        dataSource: BreakingBadRemoteDataSource
    ): IrrGetRemoteActors {
        return IrrGetRemoteActors(BreakingBadRemoteRepository(dataSource))
    }

    @Provides
    fun provideIrrGetRemoteBBActorsByName(
        dataSource: BreakingBadRemoteDataSource
    ): IrrGetRemoteActorsByName {
        return IrrGetRemoteActorsByName(BreakingBadRemoteRepository(dataSource))
    }

    @Provides
    fun provideIrrGetLocalBBActors(
        dataSource: BreakingBadLocalDataSource
    ): IrrGetLocalActors {
        return IrrGetLocalActors(BreakingBadLocalRepository(dataSource))
    }

    @Provides
    fun provideIrrGetLocalBBActor(
        dataSource: BreakingBadLocalDataSource
    ): IrrGetLocalActor {
        return IrrGetLocalActor(BreakingBadLocalRepository(dataSource))
    }

    @Provides
    fun provideIrrInsertBBActors(
        dataSource: BreakingBadLocalDataSource
    ): IrrStoreActors {
        return IrrStoreActors(BreakingBadLocalRepository(dataSource))
    }

}