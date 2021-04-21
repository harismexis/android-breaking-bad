package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Actor


sealed class ActorsResult {
    data class ActorsSuccess(val items: List<Actor>): ActorsResult()
    data class ActorsError(val error: Exception): ActorsResult()
}