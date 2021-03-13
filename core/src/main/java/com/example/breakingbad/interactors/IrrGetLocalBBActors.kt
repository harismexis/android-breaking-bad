package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBActorLocalRepository

class IrrGetLocalBBActors(
    private val repository: BBActorLocalRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
