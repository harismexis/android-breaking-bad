package com.example.breakingbad.presentation.interactors

import com.example.breakingbad.interactors.IrrGetLocalActors
import com.example.breakingbad.interactors.IrrGetRemoteActors
import com.example.breakingbad.interactors.IrrGetRemoteActorsByName
import com.example.breakingbad.interactors.IrrStoreActors

data class HomeInteractors(
    val irrGetLocalActors: IrrGetLocalActors,
    val irrGetRemoteActors: IrrGetRemoteActors,
    val irrGetRemoteActorsByName: IrrGetRemoteActorsByName,
    val irrStoreActors: IrrStoreActors
)
