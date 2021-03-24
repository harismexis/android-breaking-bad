package com.harismexis.breakingbad.presentation.screens.episodes.interactors

import com.harismexis.breakingbad.interactors.episode.IrrGetLocalEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrGetRemoteEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrStoreEpisodes

data class EpisodeInteractors(
    val irrGetRemoteEpisodes: IrrGetRemoteEpisodes,
    val irrGetLocalEpisodes: IrrGetLocalEpisodes,
    val irrStoreEpisodes: IrrStoreEpisodes
)
