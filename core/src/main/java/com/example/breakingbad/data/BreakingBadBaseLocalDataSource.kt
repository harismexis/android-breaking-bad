package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Quote

interface BreakingBadBaseLocalDataSource {

    suspend fun insertActors(items: List<Actor>)

    suspend fun getActor(itemId: Int): Actor?

    suspend fun getAllActors(): List<Actor>

    suspend fun getQuotes(): List<Quote>

    suspend fun insertQuotes(items: List<Quote>)

}