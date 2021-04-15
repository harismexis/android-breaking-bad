package com.harismexis.breakingbad.framework.di.interactor.cases

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.episode.IrrGetLocalEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrGetRemoteEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrStoreEpisodes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EpisodeCasesModule {

    @Provides
    @Singleton
    fun provideIrrGetRemoteEpisodes(
        source: BreakingBadRemoteDataSource
    ): IrrGetRemoteEpisodes {
        return IrrGetRemoteEpisodes(BreakingBadRemoteRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrGetLocalEpisodes(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalEpisodes {
        return IrrGetLocalEpisodes(BreakingBadLocalRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrStoreEpisodes(
        source: BreakingBadLocalDataSource
    ): IrrStoreEpisodes {
        return IrrStoreEpisodes(BreakingBadLocalRepository(source))
    }


}