package com.example.breakingbad.presentation.result

import com.example.breakingbad.domain.Episode

sealed class EpisodesResult {
    data class EpisodesSuccess(val items: List<Episode>): EpisodesResult()
    data class EpisodesError(val error: String): EpisodesResult()
}