package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.data.BreakingBadRemoteRepository
import com.example.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.example.breakingbad.interactors.quote.IrrGetLocalQuotes
import com.example.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.example.breakingbad.interactors.quote.IrrStoreQuotes
import com.example.breakingbad.presentation.screens.quotes.interactors.QuoteInteractors
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