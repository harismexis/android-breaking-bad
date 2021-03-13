package com.example.breakingbad.presentation.interactors

import com.example.breakingbad.interactors.IrrGetLocalBBActors
import com.example.breakingbad.interactors.IrrGetRemoteBBActors
import com.example.breakingbad.interactors.IrrGetRemoteBBActorsByName
import com.example.breakingbad.interactors.IrrStoreBBActors

data class HomeInteractors(
    val irrGetLocalBBActors: IrrGetLocalBBActors,
    val irrGetRemoteBBActors: IrrGetRemoteBBActors,
    val irrGetRemoteBBActorsByName: IrrGetRemoteBBActorsByName,
    val irrStoreBBActors: IrrStoreBBActors
)
