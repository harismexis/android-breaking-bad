package com.harismexis.breakingbad.core.repository.episode

import com.harismexis.breakingbad.core.domain.Episode

interface EpisodesLocal {

    suspend fun save(items: List<Episode>)

    suspend fun getEpisodes(): List<Episode>
}