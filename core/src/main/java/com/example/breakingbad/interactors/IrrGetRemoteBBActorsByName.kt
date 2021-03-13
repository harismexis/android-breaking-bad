package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBActorRemoteRepository

class IrrGetRemoteBBActorsByName(
    private val repository: BBActorRemoteRepository
) {
    suspend operator fun invoke(name: String?) = repository.getItemsByName(name)
}
