package com.example.breakingbad.presentation.screens.deaths.interactors

import com.example.breakingbad.interactors.death.IrrGetLocalDeaths
import com.example.breakingbad.interactors.death.IrrGetRemoteDeaths
import com.example.breakingbad.interactors.death.IrrStoreDeaths

data class DeathInteractors(
    val irrGetRemoteDeaths: IrrGetRemoteDeaths,
    val irrGetLocalDeaths: IrrGetLocalDeaths,
    val irrStoreDeaths: IrrStoreDeaths
)
