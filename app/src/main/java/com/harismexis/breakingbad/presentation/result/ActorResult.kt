package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.domain.Actor

sealed class ActorResult {
    data class ActorSuccess(val item: Actor): ActorResult()
    data class ActorError(val error: String): ActorResult()
}