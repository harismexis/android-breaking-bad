package com.example.jsonfeed.framework.datasource.network

data class PokemonCard(
    var id: String?,
    var name: String?,
    var imageUrl: String?,
    var imageUrlHiRes: String?,
    var supertype: String?,
    var subtype: String?,
    var artist: String?,
    var rarity: String?,
    var series: String?,
    var set: String?,
    var setCode: String?
)