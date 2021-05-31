package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Death

sealed class DeathsResult {
    data class Success(val items: List<Death>): DeathsResult()
    data class Error(val error: Exception): DeathsResult()
}