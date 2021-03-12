package com.example.breakingbad.presentation.interactors

import com.example.breakingbad.interactors.InterGetLocalBBCharacters
import com.example.breakingbad.interactors.InterGetRemoteBBCharacters
import com.example.breakingbad.interactors.InterStoreBBCharacters

data class HomeInteractors(
    val interGetLocalBBCharacters: InterGetLocalBBCharacters,
    val interGetRemoteBBCharacters: InterGetRemoteBBCharacters,
    val interStoreBBCharacters: InterStoreBBCharacters
)
