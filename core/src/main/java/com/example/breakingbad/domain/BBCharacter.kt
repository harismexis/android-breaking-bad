package com.example.breakingbad.domain

import java.io.Serializable

data class BBCharacter(
    val id: String,
    val name: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    val lifeSpan: String?,
    val energyLevel: Int?,
    val wikipediaUrl: String?,
    val imageUrl: String?
) : Serializable