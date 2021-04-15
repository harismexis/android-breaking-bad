package com.harismexis.breakingbad.framework.di.interactor.cases

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.death.IrrGetLocalDeaths
import com.harismexis.breakingbad.interactors.death.IrrGetRemoteDeaths
import com.harismexis.breakingbad.interactors.death.IrrStoreDeaths
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DeathCasesModule {

    @Provides
    @Singleton
    fun provideIrrGetRemoteDeaths(
        source: BreakingBadRemoteDataSource
    ): IrrGetRemoteDeaths {
        return IrrGetRemoteDeaths(BreakingBadRemoteRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrGetLocalDeaths(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalDeaths {
        return IrrGetLocalDeaths(BreakingBadLocalRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrStoreDeaths(
        source: BreakingBadLocalDataSource
    ): IrrStoreDeaths {
        return IrrStoreDeaths(BreakingBadLocalRepository(source))
    }


}