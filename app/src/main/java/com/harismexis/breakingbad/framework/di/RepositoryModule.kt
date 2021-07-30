package com.harismexis.breakingbad.framework.di

import com.harismexis.breakingbad.framework.datasource.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.datasource.database.repository.ActorsLocalRepository
import com.harismexis.breakingbad.framework.datasource.database.repository.DeathsLocalRepository
import com.harismexis.breakingbad.framework.datasource.database.repository.EpisodesLocalRepository
import com.harismexis.breakingbad.framework.datasource.database.repository.QuotesLocalRepository
import com.harismexis.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.datasource.network.repository.ActorsRemoteRepository
import com.harismexis.breakingbad.framework.datasource.network.repository.DeathsRemoteRepository
import com.harismexis.breakingbad.framework.datasource.network.repository.EpisodesRemoteRepository
import com.harismexis.breakingbad.framework.datasource.network.repository.QuotesRemoteRepository
import com.harismexis.breakingbad.model.repository.*
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideActorsRemote(api: BreakingBadApi): ActorsRemote {
        return ActorsRemoteRepository(api)
    }

    @Provides
    fun provideActorsLocal(dao: BreakingBadLocalDao): ActorsLocal {
        return ActorsLocalRepository(dao)
    }

    @Provides
    fun provideQuotesRemote(api: BreakingBadApi): QuotesRemote {
        return QuotesRemoteRepository(api)
    }

    @Provides
    fun provideQuotesLocal(dao: BreakingBadLocalDao): QuotesLocal {
        return QuotesLocalRepository(dao)
    }

    @Provides
    fun provideEpisodesRemote(api: BreakingBadApi): EpisodesRemote {
        return EpisodesRemoteRepository(api)
    }

    @Provides
    fun provideEpisodesLocal(dao: BreakingBadLocalDao): EpisodesLocal {
        return EpisodesLocalRepository(dao)
    }

    @Provides
    fun provideDeathsRemote(api: BreakingBadApi): DeathsRemote {
        return DeathsRemoteRepository(api)
    }

    @Provides
    fun provideDeathsLocal(dao: BreakingBadLocalDao): DeathsLocal {
        return DeathsLocalRepository(dao)
    }

}