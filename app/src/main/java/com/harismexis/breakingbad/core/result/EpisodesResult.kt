package com.harismexis.breakingbad.core.result

import com.harismexis.breakingbad.core.domain.Episode

sealed class EpisodesResult {
    data class Success(val items: List<Episode>): EpisodesResult()
    data class Error(val error: Exception): EpisodesResult()
}