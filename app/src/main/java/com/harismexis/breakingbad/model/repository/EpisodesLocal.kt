package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Episode

interface EpisodesLocal {

    suspend fun insertEpisodes(items: List<Episode>)

    suspend fun getEpisodes(): List<Episode>

}