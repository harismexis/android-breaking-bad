package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Death
import com.example.breakingbad.domain.Episode
import com.example.breakingbad.domain.Quote

interface BreakingBadBaseRemoteDataSource {

    suspend fun getActors(): List<Actor>

    suspend fun getActors(name: String? = null): List<Actor>

    suspend fun getQuotes(): List<Quote>

    suspend fun getDeaths(): List<Death>

    suspend fun getEpisodes(): List<Episode>

}