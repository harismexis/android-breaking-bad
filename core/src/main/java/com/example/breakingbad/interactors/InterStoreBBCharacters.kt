package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterLocalRepository
import com.example.breakingbad.domain.BBCharacter

class InterStoreBBCharacters(
    private val repository: BBCharacterLocalRepository
) {
    suspend operator fun invoke(items: List<BBCharacter>) = repository.insertItems(items)
}
