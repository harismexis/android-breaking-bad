package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.framework.extensions.episode.toItems
import com.harismexis.breakingbad.framework.extensions.episode.toLocalItems
import com.harismexis.breakingbad.framework.data.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.model.domain.Episode
import com.harismexis.breakingbad.model.repository.EpisodesLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodesLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
): EpisodesLocal {
    override suspend fun insertEpisodes(items: List<Episode>) {
        dao.insertEpisodes(items.toLocalItems())
    }

    override suspend fun getEpisodes(): List<Episode> {
        return dao.getAllEpisodes().toItems()
    }

}