package com.harismexis.breakingbad.model.result

import com.harismexis.breakingbad.model.domain.Actor

sealed class ActorsResult {
    data class Success(val items: List<Actor>): ActorsResult()
    data class Error(val error: Exception): ActorsResult()
}