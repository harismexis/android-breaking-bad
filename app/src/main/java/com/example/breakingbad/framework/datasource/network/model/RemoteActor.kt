package com.example.breakingbad.framework.datasource.network.model

data class RemoteActor(
    val char_id: Int?,
    val name: String?,
    val birthday: String?, //"09-07-1958"
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
)
