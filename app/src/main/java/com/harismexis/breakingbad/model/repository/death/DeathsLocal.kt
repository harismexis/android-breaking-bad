package com.harismexis.breakingbad.model.repository.death

import com.harismexis.breakingbad.model.domain.Death

interface DeathsLocal {

    suspend fun insertDeaths(items: List<Death>)

    suspend fun getDeaths(): List<Death>

}