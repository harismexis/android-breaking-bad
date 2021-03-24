package com.harismexis.breakingbad.presentation.screens.quotes.interactors

import com.harismexis.breakingbad.interactors.quote.IrrGetLocalQuotes
import com.harismexis.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.harismexis.breakingbad.interactors.quote.IrrStoreQuotes

data class QuoteInteractors(
    val irrGetRemoteQuotes: IrrGetRemoteQuotes,
    val irrGetLocalQuotes: IrrGetLocalQuotes,
    val irrStoreQuotes: IrrStoreQuotes
)
