package com.harismexis.breakingbad.framework.di

import com.harismexis.breakingbad.core.repository.actor.ActorsLocal
import com.harismexis.breakingbad.core.repository.actor.ActorsRemote
import com.harismexis.breakingbad.core.repository.death.DeathsLocal
import com.harismexis.breakingbad.core.repository.death.DeathsRemote
import com.harismexis.breakingbad.core.repository.episode.EpisodesLocal
import com.harismexis.breakingbad.core.repository.episode.EpisodesRemote
import com.harismexis.breakingbad.core.repository.quote.QuotesLocal
import com.harismexis.breakingbad.core.repository.quote.QuotesRemote
import com.harismexis.breakingbad.core.repository.video.VideosLocal
import com.harismexis.breakingbad.framework.data.database.dao.*
import com.harismexis.breakingbad.framework.data.database.repository.*
import com.harismexis.breakingbad.framework.data.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.data.network.repository.ActorsRemoteRepository
import com.harismexis.breakingbad.framework.data.network.repository.DeathsRemoteRepository
import com.harismexis.breakingbad.framework.data.network.repository.EpisodesRemoteRepository
import com.harismexis.breakingbad.framework.data.network.repository.QuotesRemoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideActorsRemote(api: BreakingBadApi): ActorsRemote {
        return ActorsRemoteRepository(api)
    }

    @Provides
    fun provideQuotesRemote(api: BreakingBadApi): QuotesRemote {
        return QuotesRemoteRepository(api)
    }

    @Provides
    fun provideEpisodesRemote(api: BreakingBadApi): EpisodesRemote {
        return EpisodesRemoteRepository(api)
    }

    @Provides
    fun provideDeathsRemote(api: BreakingBadApi): DeathsRemote {
        return DeathsRemoteRepository(api)
    }

    @Provides
    fun provideActorsLocal(dao: ActorsDao): ActorsLocal {
        return ActorsLocalRepository(dao)
    }

    @Provides
    fun provideQuotesLocal(dao: QuotesDao): QuotesLocal {
        return QuotesLocalRepository(dao)
    }

    @Provides
    fun provideEpisodesLocal(dao: EpisodesDao): EpisodesLocal {
        return EpisodesLocalRepository(dao)
    }

    @Provides
    fun provideDeathsLocal(dao: DeathsDao): DeathsLocal {
        return DeathsLocalRepository(dao)
    }

    @Provides
    fun provideVideosLocal(dao: VideosDao): VideosLocal {
        return VideosLocalRepository(dao)
    }

}