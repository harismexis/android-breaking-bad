package com.harismexis.breakingbad.domain

import java.io.Serializable

data class Quote(
    val quote_id: Int,
    val quote: String?,
    val author: String?,
    val series: String?
) : Serializable