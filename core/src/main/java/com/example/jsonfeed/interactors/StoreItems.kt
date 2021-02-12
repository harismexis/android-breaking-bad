package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.domain.Item

class StoreItems(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(items: List<Item>) = repository.insertItems(items)
}
