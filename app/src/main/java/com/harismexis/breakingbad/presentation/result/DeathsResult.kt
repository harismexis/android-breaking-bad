package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Death


sealed class DeathsResult {
    data class DeathsSuccess(val items: List<Death>): DeathsResult()
    data class DeathsError(val error: Exception): DeathsResult()
}