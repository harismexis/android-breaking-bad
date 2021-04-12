package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.domain.Quote

sealed class QuotesResult {
    data class QuotesSuccess(val items: List<Quote>): QuotesResult()
    data class QuotesError(val error: Exception): QuotesResult()
}