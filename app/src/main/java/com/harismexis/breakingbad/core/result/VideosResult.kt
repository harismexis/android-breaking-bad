package com.harismexis.breakingbad.core.result

import com.harismexis.breakingbad.core.domain.Video

sealed class VideosResult {
    data class Success(val items: List<Video>): VideosResult()
    data class Error(val error: Exception): VideosResult()
}