package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.core.domain.Episode
import com.harismexis.breakingbad.core.repository.episode.EpisodesLocal
import com.harismexis.breakingbad.framework.data.database.dao.EpisodesDao
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodesLocalRepository @Inject constructor(
    private val dao: EpisodesDao
) : EpisodesLocal {

    override suspend fun save(items: List<Episode>) {
        dao.delete()
        dao.insert(items.toLocalItems())
    }

    override suspend fun getEpisodes(): List<Episode> {
        return dao.getAll().toItems()
    }

}