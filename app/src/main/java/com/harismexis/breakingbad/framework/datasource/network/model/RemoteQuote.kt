package com.harismexis.breakingbad.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class RemoteQuote(
    @SerializedName("quote_id")
    val quoteId: Int?,
    val quote: String?,
    val author: String?,
    val series: String?
)
