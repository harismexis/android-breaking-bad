package com.example.breakingbad.presentation.result

import com.example.breakingbad.domain.Death

sealed class DeathsResult {
    data class DeathsSuccess(val items: List<Death>): DeathsResult()
    data class DeathsError(val error: String): DeathsResult()
}