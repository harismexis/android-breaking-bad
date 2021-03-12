package com.example.breakingbad.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class BBCharacterRemoteItem(
    val id: String?,
    val name: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("energy_level")
    val energyLevel: Int?,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?,
    var image: BBCharImage?
)
