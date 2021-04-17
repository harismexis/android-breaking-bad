package com.harismexis.breakingbad.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class RemoteActor(
    @SerializedName("char_id")
    val actorId: Int?,
    val name: String?,
    val birthday: String?,
    var occupation: List<String>?,
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
)
