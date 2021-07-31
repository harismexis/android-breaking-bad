package com.harismexis.breakingbad.core.result

import com.harismexis.breakingbad.core.domain.Actor

sealed class ActorDetailResult {
    data class Success(val item: Actor): ActorDetailResult()
    data class Error(val error: String): ActorDetailResult()
}