package com.harismexis.breakingbad.framework.datasource.network.model

data class RemoteActor(
    val char_id: Int?,
    val name: String?,
    val birthday: String?,
    var occupation: List<String>?,
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
)
