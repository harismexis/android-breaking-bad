package com.harismexis.breakingbad.core.result

import com.harismexis.breakingbad.core.domain.Quote

sealed class QuotesResult {
    data class Success(val items: List<Quote>): QuotesResult()
    data class Error(val error: Exception): QuotesResult()
}