package com.harismexis.breakingbad.presentation.screens.quotes.interactors

import com.harismexis.breakingbad.interactors.quote.IrrGetLocalQuotesBySeries
import com.harismexis.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.harismexis.breakingbad.interactors.quote.IrrStoreQuotes

data class QuoteInteractors(
    val irrGetRemoteQuotes: IrrGetRemoteQuotes,
    val irrGetLocalQuotesBySeries: IrrGetLocalQuotesBySeries,
    val irrStoreQuotes: IrrStoreQuotes
)
