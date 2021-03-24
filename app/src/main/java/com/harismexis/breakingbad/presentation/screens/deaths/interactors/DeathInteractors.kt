package com.harismexis.breakingbad.presentation.screens.deaths.interactors

import com.harismexis.breakingbad.interactors.death.IrrGetLocalDeaths
import com.harismexis.breakingbad.interactors.death.IrrGetRemoteDeaths
import com.harismexis.breakingbad.interactors.death.IrrStoreDeaths

data class DeathInteractors(
    val irrGetRemoteDeaths: IrrGetRemoteDeaths,
    val irrGetLocalDeaths: IrrGetLocalDeaths,
    val irrStoreDeaths: IrrStoreDeaths
)
