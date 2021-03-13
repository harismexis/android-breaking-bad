package com.example.breakingbad.data

import com.example.breakingbad.domain.BBActor

interface BBActorBaseLocalDataSource {

    suspend fun insert(items: List<BBActor>)

    suspend fun getItem(itemId: Int): BBActor?

    suspend fun getAll(): List<BBActor>
}