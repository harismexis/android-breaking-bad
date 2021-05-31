package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Quote

sealed class QuotesResult {
    data class Success(val items: List<Quote>): QuotesResult()
    data class Error(val error: Exception): QuotesResult()
}