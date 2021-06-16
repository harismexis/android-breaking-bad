package com.harismexis.breakingbad.model.result

import com.harismexis.breakingbad.model.domain.Actor

sealed class ActorDetailResult {
    data class Success(val item: Actor): ActorDetailResult()
    data class Error(val error: String): ActorDetailResult()
}