package com.harismexis.breakingbad.framework.di.interactor.cases

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.quote.IrrGetLocalQuotes
import com.harismexis.breakingbad.interactors.quote.IrrGetLocalQuotesBySeries
import com.harismexis.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.harismexis.breakingbad.interactors.quote.IrrStoreQuotes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class QuoteCasesModule {

    @Provides
    @Singleton
    fun provideIrrGetRemoteQuotes(
        source: BreakingBadRemoteDataSource
    ): IrrGetRemoteQuotes {
        return IrrGetRemoteQuotes(BreakingBadRemoteRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrGetLocalQuotes(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalQuotes {
        return IrrGetLocalQuotes(BreakingBadLocalRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrGetLocalQuotesBySeries(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalQuotesBySeries {
        return IrrGetLocalQuotesBySeries(BreakingBadLocalRepository(source))
    }

    @Provides
    @Singleton
    fun provideIrrStoreQuotes(
        source: BreakingBadLocalDataSource
    ): IrrStoreQuotes {
        return IrrStoreQuotes(BreakingBadLocalRepository(source))
    }


}