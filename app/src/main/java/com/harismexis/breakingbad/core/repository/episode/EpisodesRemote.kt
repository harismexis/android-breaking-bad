package com.harismexis.breakingbad.core.repository.episode

import com.harismexis.breakingbad.core.domain.Episode


interface EpisodesRemote{

    suspend fun getEpisodes(series: String? = null): List<Episode>
}