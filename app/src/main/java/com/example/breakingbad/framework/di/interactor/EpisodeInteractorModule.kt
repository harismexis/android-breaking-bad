package com.example.breakingbad.framework.di.interactor

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.data.BreakingBadRemoteRepository
import com.example.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.example.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.example.breakingbad.interactors.episode.IrrGetLocalEpisodes
import com.example.breakingbad.interactors.episode.IrrGetRemoteEpisodes
import com.example.breakingbad.interactors.episode.IrrStoreEpisodes
import com.example.breakingbad.presentation.screens.episodes.interactors.EpisodeInteractors
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