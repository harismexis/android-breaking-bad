package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.domain.Actor

sealed class ActorDetailResult {
    data class ActorSuccess(val item: Actor): ActorDetailResult()
    data class ActorError(val error: String): ActorDetailResult()
}