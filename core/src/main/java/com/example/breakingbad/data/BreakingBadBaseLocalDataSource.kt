package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

interface BreakingBadBaseLocalDataSource {

    suspend fun insert(items: List<Actor>)

    suspend fun getItem(itemId: Int): Actor?

    suspend fun getAll(): List<Actor>
}