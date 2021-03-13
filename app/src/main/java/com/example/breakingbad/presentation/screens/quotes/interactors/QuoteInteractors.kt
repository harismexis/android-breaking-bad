package com.example.breakingbad.presentation.screens.quotes.interactors

import com.example.breakingbad.interactors.quote.IrrGetLocalQuotes
import com.example.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.example.breakingbad.interactors.quote.IrrStoreQuotes

data class QuoteInteractors(
    val irrGetRemoteQuotes: IrrGetRemoteQuotes,
    val irrGetLocalQuotes: IrrGetLocalQuotes,
    val irrStoreQuotes: IrrStoreQuotes
)
