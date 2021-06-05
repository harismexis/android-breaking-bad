package com.harismexis.breakingbad.datamodel.repository

import com.harismexis.breakingbad.datamodel.domain.Episode
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.episode.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class EpisodesRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getEpisodes(series: String? = null): List<Episode> = dao.getEpisodes(series).toItems()

}