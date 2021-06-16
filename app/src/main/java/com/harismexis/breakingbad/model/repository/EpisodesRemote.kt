package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Episode


interface EpisodesRemote{

    suspend fun getEpisodes(series: String? = null): List<Episode>

}