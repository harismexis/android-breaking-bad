package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBActorRemoteRepository

class IrrGetRemoteBBActors(
    private val repository: BBActorRemoteRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
