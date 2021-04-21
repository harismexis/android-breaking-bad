package com.harismexis.breakingbad.presentation.screens.deaths.model

import com.harismexis.breakingbad.datamodel.domain.Death
import com.harismexis.breakingbad.datamodel.repo.DeathLocalRepo
import com.harismexis.breakingbad.datamodel.repo.DeathRemoteRepo
import javax.inject.Inject

data class DeathsModel @Inject constructor (
    private val deathRemote: DeathRemoteRepo,
    private val deathLocal: DeathLocalRepo
) {
    suspend fun getRemoteDeaths(): List<Death> = deathRemote.getDeaths()

    suspend fun insertDeaths(items: List<Death>) {
        deathLocal.insertDeaths(items)
    }

    suspend fun getLocalDeaths(): List<Death> {
        return deathLocal.getDeaths()
    }


}