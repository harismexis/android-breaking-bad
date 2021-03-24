package com.harismexis.breakingbad.framework.di.interactor

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.death.IrrGetLocalDeaths
import com.harismexis.breakingbad.interactors.death.IrrGetRemoteDeaths
import com.harismexis.breakingbad.interactors.death.IrrStoreDeaths
import com.harismexis.breakingbad.presentation.screens.deaths.interactors.DeathInteractors
import dagger.Module
import dagger.Provides

@Module
class DeathInteractorModule {

    @Provides
    fun provideDeathIrrs(
        irrGetRemoteDeaths: IrrGetRemoteDeaths,
        irrGetLocalDeaths: IrrGetLocalDeaths,
        irrStoreDeaths: IrrStoreDeaths
    ): DeathInteractors {
        return DeathInteractors(
            irrGetRemoteDeaths,
            irrGetLocalDeaths,
            irrStoreDeaths
        )
    }

    @Provides
    fun provideIrrGetRemoteDeaths(
        source: BreakingBadRemoteDataSource
    ): IrrGetRemoteDeaths {
        return IrrGetRemoteDeaths(BreakingBadRemoteRepository(source))
    }

    @Provides
    fun provideIrrGetLocalDeaths(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalDeaths {
        return IrrGetLocalDeaths(BreakingBadLocalRepository(source))
    }

    @Provides
    fun provideIrrStoreDeaths(
        source: BreakingBadLocalDataSource
    ): IrrStoreDeaths {
        return IrrStoreDeaths(BreakingBadLocalRepository(source))
    }


}