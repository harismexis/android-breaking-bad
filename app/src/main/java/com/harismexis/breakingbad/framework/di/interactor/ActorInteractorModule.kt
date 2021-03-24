package com.harismexis.breakingbad.framework.di.interactor

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActor
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActors
import com.harismexis.breakingbad.interactors.actor.IrrGetRemoteActors
import com.harismexis.breakingbad.interactors.actor.IrrStoreActors
import dagger.Module
import dagger.Provides

@Module
class ActorInteractorModule {

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