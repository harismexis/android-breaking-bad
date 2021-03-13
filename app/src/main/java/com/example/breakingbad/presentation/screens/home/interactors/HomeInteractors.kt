package com.example.breakingbad.presentation.screens.home.interactors

import com.example.breakingbad.interactors.actor.IrrGetLocalActors
import com.example.breakingbad.interactors.actor.IrrGetRemoteActors
import com.example.breakingbad.interactors.actor.IrrGetRemoteActorsByName
import com.example.breakingbad.interactors.actor.IrrStoreActors

data class HomeInteractors(
    val irrGetLocalActors: IrrGetLocalActors,
    val irrGetRemoteActors: IrrGetRemoteActors,
    val irrGetRemoteActorsByName: IrrGetRemoteActorsByName,
    val irrStoreActors: IrrStoreActors
)