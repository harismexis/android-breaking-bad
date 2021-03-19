package com.example.breakingbad.domain

import java.io.Serializable

data class Actor(
    val char_id: Int,
    val name: String?,
    val birthday: String?,
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
) : Serializable