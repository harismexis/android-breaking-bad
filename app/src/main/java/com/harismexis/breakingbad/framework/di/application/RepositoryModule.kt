package com.harismexis.breakingbad.framework.di.application

import com.harismexis.breakingbad.framework.datasource.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.datasource.database.repository.ActorsLocalRepository
import com.harismexis.breakingbad.framework.datasource.database.repository.DeathsLocalRepository
import com.harismexis.breakingbad.framework.datasource.database.repository.EpisodesLocalRepository
import com.harismexis.breakingbad.framework.datasource.database.repository.QuotesLocalRepository
import com.harismexis.breakingbad.framework.datasource.network.dao.BreakingBadRemoteDao
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
    fun provideActorsRemote(dao: BreakingBadRemoteDao): ActorsRemote {
        return ActorsRemoteRepository(dao)
    }

    @Provides
    fun provideActorsLocal(dao: BreakingBadLocalDao): ActorsLocal {
        return ActorsLocalRepository(dao)
    }

    @Provides
    fun provideQuotesRemote(dao: BreakingBadRemoteDao): QuotesRemote {
        return QuotesRemoteRepository(dao)
    }

    @Provides
    fun provideQuotesLocal(dao: BreakingBadLocalDao): QuotesLocal {
        return QuotesLocalRepository(dao)
    }

    @Provides
    fun provideEpisodesRemote(dao: BreakingBadRemoteDao): EpisodesRemote {
        return EpisodesRemoteRepository(dao)
    }

    @Provides
    fun provideEpisodesLocal(dao: BreakingBadLocalDao): EpisodesLocal {
        return EpisodesLocalRepository(dao)
    }

    @Provides
    fun provideDeathsRemote(dao: BreakingBadRemoteDao): DeathsRemote {
        return DeathsRemoteRepository(dao)
    }

    @Provides
    fun provideDeathsLocal(dao: BreakingBadLocalDao): DeathsLocal {
        return DeathsLocalRepository(dao)
    }

}