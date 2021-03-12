package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterRemoteRepository

class InterGetRemoteBBCharacters(
    private val repository: BBCharacterRemoteRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
