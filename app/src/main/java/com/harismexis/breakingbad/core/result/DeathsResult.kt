package com.harismexis.breakingbad.core.result

import com.harismexis.breakingbad.core.domain.Death

sealed class DeathsResult {
    data class Success(val items: List<Death>): DeathsResult()
    data class Error(val error: Exception): DeathsResult()
}