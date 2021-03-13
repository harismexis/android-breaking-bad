package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Quote

interface BreakingBadBaseRemoteDataSource {

    suspend fun getActors(): List<Actor>

    suspend fun getActorsByName(name: String?): List<Actor>

    suspend fun getQuotes(): List<Quote>

}