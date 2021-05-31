package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Episode

sealed class EpisodesResult {
    data class Success(val items: List<Episode>): EpisodesResult()
    data class Error(val error: Exception): EpisodesResult()
}