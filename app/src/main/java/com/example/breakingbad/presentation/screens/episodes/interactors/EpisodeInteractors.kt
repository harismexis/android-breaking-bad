package com.example.breakingbad.presentation.screens.episodes.interactors

import com.example.breakingbad.interactors.episode.IrrGetLocalEpisodes
import com.example.breakingbad.interactors.episode.IrrGetRemoteEpisodes
import com.example.breakingbad.interactors.episode.IrrStoreEpisodes

data class EpisodeInteractors(
    val irrGetRemoteEpisodes: IrrGetRemoteEpisodes,
    val irrGetLocalEpisodes: IrrGetLocalEpisodes,
    val irrStoreEpisodes: IrrStoreEpisodes
)
