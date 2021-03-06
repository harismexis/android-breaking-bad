package com.harismexis.breakingbad.model.result

import com.harismexis.breakingbad.model.domain.Death

sealed class DeathsResult {
    data class Success(val items: List<Death>): DeathsResult()
    data class Error(val error: Exception): DeathsResult()
}