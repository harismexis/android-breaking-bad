package com.example.jsonfeed.data

import com.example.jsonfeed.domain.Item

interface RemoteDataSource {

    suspend fun getItems(): List<Item>?

}