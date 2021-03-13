package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBActorLocalRepository

class IrrGetLocalBBActor(
    private val repository: BBActorLocalRepository
) {
    suspend operator fun invoke(itemId: Int) = repository.getItem(itemId)
}
