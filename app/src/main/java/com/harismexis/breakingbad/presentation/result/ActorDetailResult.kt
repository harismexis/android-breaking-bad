package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Actor

sealed class ActorDetailResult {
    data class Success(val item: Actor): ActorDetailResult()
    data class Error(val error: String): ActorDetailResult()
}