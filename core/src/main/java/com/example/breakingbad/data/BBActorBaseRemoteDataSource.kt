package com.example.breakingbad.data

import com.example.breakingbad.domain.BBActor

interface BBActorBaseRemoteDataSource {

    suspend fun getItems(): List<BBActor>

    suspend fun getItemsByName(name: String?): List<BBActor>

}