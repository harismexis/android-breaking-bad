package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.database.BBActorLocalDataSource
import com.example.breakingbad.data.BBActorLocalRepository
import com.example.breakingbad.data.BBActorRemoteRepository
import com.example.breakingbad.presentation.interactors.BBActorDetailInteractors
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.datasource.network.data.BBActorRemoteDataSource
import com.example.breakingbad.interactors.*
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideHomeInteractors(
        irrGetLocalBBActors: IrrGetLocalBBActors,
        irrGetRemoteBBActors: IrrGetRemoteBBActors,
        irrGetRemoteBBActorsByName: IrrGetRemoteBBActorsByName,
        irrStoreBBActors: IrrStoreBBActors
    ): HomeInteractors {
        return HomeInteractors(
            irrGetLocalBBActors,
            irrGetRemoteBBActors,
            irrGetRemoteBBActorsByName,
            irrStoreBBActors
        )
    }

    @Provides
    fun provideBBActorDetailInteractors(
        irrGetLocalBBActor: IrrGetLocalBBActor
    ): BBActorDetailInteractors {
        return BBActorDetailInteractors(irrGetLocalBBActor)
    }

    @Provides
    fun provideIrrGetRemoteBBActors(
        dataSource: BBActorRemoteDataSource
    ): IrrGetRemoteBBActors {
        return IrrGetRemoteBBActors(BBActorRemoteRepository(dataSource))
    }

    @Provides
    fun provideIrrGetRemoteBBActorsByName(
        dataSource: BBActorRemoteDataSource
    ): IrrGetRemoteBBActorsByName {
        return IrrGetRemoteBBActorsByName(BBActorRemoteRepository(dataSource))
    }

    @Provides
    fun provideIrrGetLocalBBActors(
        dataSource: BBActorLocalDataSource
    ): IrrGetLocalBBActors {
        return IrrGetLocalBBActors(BBActorLocalRepository(dataSource))
    }

    @Provides
    fun provideIrrGetLocalBBActor(
        dataSource: BBActorLocalDataSource
    ): IrrGetLocalBBActor {
        return IrrGetLocalBBActor(BBActorLocalRepository(dataSource))
    }

    @Provides
    fun provideIrrInsertBBActors(
        dataSource: BBActorLocalDataSource
    ): IrrStoreBBActors {
        return IrrStoreBBActors(BBActorLocalRepository(dataSource))
    }

}