package com.harismexis.breakingbad.datamodel.domain

data class Actor(
    val actorId: Int,
    val name: String?,
    val birthday: String?,
    var occupation: List<String>?,
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
) {

    companion object {

        fun Actor?.occupationString(): String? {
            return this?.occupation?.joinToString(",  ")
        }

    }
}