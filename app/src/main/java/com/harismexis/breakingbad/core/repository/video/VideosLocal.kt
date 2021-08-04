package com.harismexis.breakingbad.core.repository.video

import com.harismexis.breakingbad.core.domain.Video

interface VideosLocal {

    suspend fun save(items: List<Video>)

    suspend fun getVideos(): List<Video>
}