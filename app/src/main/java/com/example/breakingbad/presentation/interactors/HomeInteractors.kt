package com.example.breakingbad.presentation.interactors

import com.example.breakingbad.interactors.InterGetLocalBBCharacters
import com.example.breakingbad.interactors.InterGetRemoteBBCharacters
import com.example.breakingbad.interactors.InterGetRemoteBBCharactersByName
import com.example.breakingbad.interactors.InterStoreBBCharacters

data class HomeInteractors(
    val irrGetLocalBBCharacters: InterGetLocalBBCharacters,
    val irrGetRemoteBBCharacters: InterGetRemoteBBCharacters,
    val irrGetRemoteBBCharactersByName: InterGetRemoteBBCharactersByName,
    val irrStoreBBCharacters: InterStoreBBCharacters
)
