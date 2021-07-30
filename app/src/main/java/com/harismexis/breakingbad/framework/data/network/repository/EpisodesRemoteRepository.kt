package com.harismexis.breakingbad.framework.data.network.repository

import com.harismexis.breakingbad.framework.data.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.data.network.model.toItems
import com.harismexis.breakingbad.model.domain.Episode
import com.harismexis.breakingbad.model.repository.EpisodesRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class EpisodesRemoteRepository @Inject constructor(
    private val api: BreakingBadApi
): EpisodesRemote {

    override suspend fun getEpisodes(series: String?): List<Episode> {
        return api.getEpisodes(series).toItems()
    }

}