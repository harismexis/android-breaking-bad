package com.example.breakingbad.presentation.result

import com.example.breakingbad.domain.Quote

sealed class QuotesResult {
    data class QuotesSuccess(val items: List<Quote>): QuotesResult()
    data class QuotesError(val error: String): QuotesResult()
}