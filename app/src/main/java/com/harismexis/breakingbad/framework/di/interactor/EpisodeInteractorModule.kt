package com.harismexis.breakingbad.framework.di.interactor

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.episode.IrrGetLocalEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrGetRemoteEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrStoreEpisodes
import com.harismexis.breakingbad.presentation.screens.episodes.interactors.EpisodeInteractors
import dagger.Module
import dagger.Provides

@Module
class EpisodeInteractorModule {

    @Provides
    fun provideEpisodeIrrs(
        irrGetRemoteEpisodes: IrrGetRemoteEpisodes,
        irrGetLocalEpisodes: IrrGetLocalEpisodes,
        irrStoreEpisodes: IrrStoreEpisodes
    ): EpisodeInteractors {
        return EpisodeInteractors(
            irrGetRemoteEpisodes,
            irrGetLocalEpisodes,
            irrStoreEpisodes
        )
    }

    @Provides
    fun provideIrrGetRemoteEpisodes(
        source: BreakingBadRemoteDataSource
    ): IrrGetRemoteEpisodes {
        return IrrGetRemoteEpisodes(BreakingBadRemoteRepository(source))
    }

    @Provides
    fun provideIrrGetLocalEpisodes(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalEpisodes {
        return IrrGetLocalEpisodes(BreakingBadLocalRepository(source))
    }

    @Provides
    fun provideIrrStoreEpisodes(
        source: BreakingBadLocalDataSource
    ): IrrStoreEpisodes {
        return IrrStoreEpisodes(BreakingBadLocalRepository(source))
    }


}