package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository

class GetLocalItems(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
