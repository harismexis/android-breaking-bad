package com.harismexis.breakingbad.framework.di.interactor

import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.data.BreakingBadRemoteRepository
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.interactors.quote.IrrGetLocalQuotes
import com.harismexis.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.harismexis.breakingbad.interactors.quote.IrrStoreQuotes
import com.harismexis.breakingbad.presentation.screens.quotes.interactors.QuoteInteractors
import dagger.Module
import dagger.Provides

@Module
class QuoteInteractorModule {

    @Provides
    fun provideQuoteIrrs(
        irrGetRemoteQuotes: IrrGetRemoteQuotes,
        irrGetLocalQuotes: IrrGetLocalQuotes,
        irrStoreQuotes: IrrStoreQuotes
    ): QuoteInteractors {
        return QuoteInteractors(
            irrGetRemoteQuotes,
            irrGetLocalQuotes,
            irrStoreQuotes
        )
    }

    @Provides
    fun provideIrrGetRemoteQuotes(
        source: BreakingBadRemoteDataSource
    ): IrrGetRemoteQuotes {
        return IrrGetRemoteQuotes(BreakingBadRemoteRepository(source))
    }

    @Provides
    fun provideIrrGetLocalQuotes(
        source: BreakingBadLocalDataSource
    ): IrrGetLocalQuotes {
        return IrrGetLocalQuotes(BreakingBadLocalRepository(source))
    }

    @Provides
    fun provideIrrStoreQuotes(
        source: BreakingBadLocalDataSource
    ): IrrStoreQuotes {
        return IrrStoreQuotes(BreakingBadLocalRepository(source))
    }


}