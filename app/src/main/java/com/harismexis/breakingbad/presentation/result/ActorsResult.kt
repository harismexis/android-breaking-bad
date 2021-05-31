package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Actor

sealed class ActorsResult {
    data class Success(val items: List<Actor>): ActorsResult()
    data class Error(val error: Exception): ActorsResult()
}