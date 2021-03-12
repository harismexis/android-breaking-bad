package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterLocalRepository

class InterGetLocalBBCharacter(
    private val repository: BBCharacterLocalRepository
) {
    suspend operator fun invoke(itemId: String) = repository.getItem(itemId)
}
