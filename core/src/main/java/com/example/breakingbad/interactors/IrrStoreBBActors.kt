package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBActorLocalRepository
import com.example.breakingbad.domain.BBActor

class IrrStoreBBActors(
    private val repository: BBActorLocalRepository
) {
    suspend operator fun invoke(items: List<BBActor>) = repository.insertItems(items)
}
