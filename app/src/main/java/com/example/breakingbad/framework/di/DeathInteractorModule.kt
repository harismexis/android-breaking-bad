package com.example.breakingbad.framework.di

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.data.BreakingBadRemoteRepository
import com.example.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.example.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.example.breakingbad.interactors.death.IrrGetLocalDeaths
import com.example.breakingbad.interactors.death.IrrGetRemoteDeaths
import com.example.breakingbad.interactors.death.IrrStoreDeaths
import com.example.breakingbad.presentation.screens.deaths.interactors.DeathInteractors
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