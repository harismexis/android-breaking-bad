package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Episode
import com.harismexis.breakingbad.model.datasource.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.extensions.episode.toItems
import com.harismexis.breakingbad.framework.extensions.episode.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodesLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
) {
    suspend fun insertEpisodes(items: List<Episode>) {
        dao.insertEpisodes(items.toLocalItems())
    }

    suspend fun getEpisodes(): List<Episode> {
        return dao.getAllEpisodes().toItems()
    }

}