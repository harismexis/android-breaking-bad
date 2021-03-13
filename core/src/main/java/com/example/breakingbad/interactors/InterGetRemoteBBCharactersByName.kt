package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterRemoteRepository

class InterGetRemoteBBCharactersByName(
    private val repository: BBCharacterRemoteRepository
) {
    suspend operator fun invoke(name: String?) = repository.getItemsByName(name)
}
