package com.harismexis.breakingbad.framework.di.interactor.cases

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
import javax.inject.Singleton

@Module
class ActorCasesModule {

    @Provides
    @Singleton
    fun provideIrrGetRemoteActors(
        dataSource: BreakingBadRemoteDataSource
    ): IrrGetRemoteActors {
        return IrrGetRemoteActors(BreakingBadRemoteRepository(dataSource))
    }

    @Provides
    @Singleton
    fun provideIrrGetLocalActors(
        dataSource: BreakingBadLocalDataSource
    ): IrrGetLocalActors {
        return IrrGetLocalActors(BreakingBadLocalRepository(dataSource))
    }

    @Provides
    @Singleton
    fun provideIrrGetLocalActor(
        dataSource: BreakingBadLocalDataSource
    ): IrrGetLocalActor {
        return IrrGetLocalActor(BreakingBadLocalRepository(dataSource))
    }

    @Provides
    @Singleton
    fun provideIrrStoreActors(
        dataSource: BreakingBadLocalDataSource
    ): IrrStoreActors {
        return IrrStoreActors(BreakingBadLocalRepository(dataSource))
    }

}