package com.harismexis.breakingbad.presentation.screens.home.interactors

import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActors
import com.harismexis.breakingbad.interactors.actor.IrrGetRemoteActors
import com.harismexis.breakingbad.interactors.actor.IrrStoreActors

data class HomeInteractors(
    val irrGetLocalActors: IrrGetLocalActors,
    val irrGetRemoteActors: IrrGetRemoteActors,
    val irrStoreActors: IrrStoreActors
)
