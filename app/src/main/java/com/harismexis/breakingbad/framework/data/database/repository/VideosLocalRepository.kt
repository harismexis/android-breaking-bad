package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.core.repository.video.VideosLocal
import com.harismexis.breakingbad.framework.data.database.dao.VideosDao
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideosLocalRepository @Inject constructor(
    private val dao: VideosDao
) : VideosLocal {

    override suspend fun save(items: List<Video>) {
        dao.delete()
        dao.insert(items.toLocalItems())
    }

    override suspend fun getVideos(): List<Video> {
        return dao.getAll().toItems()
    }

}