package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Episode


sealed class EpisodesResult {
    data class EpisodesSuccess(val items: List<Episode>): EpisodesResult()
    data class EpisodesError(val error: Exception): EpisodesResult()
}