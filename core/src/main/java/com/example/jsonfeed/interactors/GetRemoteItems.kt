package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.RemoteRepository

class GetRemoteItems(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
