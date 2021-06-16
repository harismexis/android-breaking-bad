package com.harismexis.breakingbad.model.result

import com.harismexis.breakingbad.model.domain.Episode

sealed class EpisodesResult {
    data class Success(val items: List<Episode>): EpisodesResult()
    data class Error(val error: Exception): EpisodesResult()
}