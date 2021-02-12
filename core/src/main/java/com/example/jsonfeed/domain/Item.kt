package com.example.jsonfeed.domain

import java.io.Serializable

data class Item(
    val id: String,
    val name: String?,
    val imageUrl: String?,
    val imageUrlHiRes: String?,
    val supertype: String?,
    val subtype: String?,
    val artist: String?,
    val rarity: String?,
    val series: String?,
    val set: String?,
    val setCode: String?
) : Serializable