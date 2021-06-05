package com.harismexis.breakingbad.datamodel.repository

import com.harismexis.breakingbad.datamodel.domain.Episode
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
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