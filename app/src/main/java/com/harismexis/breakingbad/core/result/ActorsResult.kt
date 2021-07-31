package com.harismexis.breakingbad.core.result

import com.harismexis.breakingbad.core.domain.Actor

sealed class ActorsResult {
    data class Success(val items: List<Actor>): ActorsResult()
    data class Error(val error: Exception): ActorsResult()
}