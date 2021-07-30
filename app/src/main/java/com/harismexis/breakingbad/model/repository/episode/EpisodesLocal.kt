package com.harismexis.breakingbad.model.repository.episode

import com.harismexis.breakingbad.model.domain.Episode

interface EpisodesLocal {

    suspend fun insertEpisodes(items: List<Episode>)

    suspend fun getEpisodes(): List<Episode>

}