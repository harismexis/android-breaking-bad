package com.example.breakingbad.presentation.result

import com.example.breakingbad.domain.Actor

sealed class ActorsResult {
    data class ActorsSuccess(val items: List<Actor>): ActorsResult()
    data class ActorsError(val error: String): ActorsResult()
}