package com.harismexis.breakingbad.framework.datasource.network.repository

import com.harismexis.breakingbad.framework.extensions.episode.toItems
import com.harismexis.breakingbad.framework.datasource.network.dao.BreakingBadRemoteDao
import com.harismexis.breakingbad.model.domain.Episode
import com.harismexis.breakingbad.model.repository.EpisodesRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class EpisodesRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
): EpisodesRemote {
    override suspend fun getEpisodes(series: String?): List<Episode> = dao.getEpisodes(series).toItems()
}