package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterLocalRepository

class InterGetLocalBBCharacters(
    private val repository: BBCharacterLocalRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
