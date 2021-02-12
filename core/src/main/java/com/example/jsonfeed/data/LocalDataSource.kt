package com.example.jsonfeed.data

import com.example.jsonfeed.domain.Item

interface LocalDataSource {

    suspend fun insert(items: List<Item>)

    suspend fun getItem(itemId: String): Item?

    suspend fun getAll(): List<Item>?
}