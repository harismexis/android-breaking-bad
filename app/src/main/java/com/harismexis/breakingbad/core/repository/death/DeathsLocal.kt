package com.harismexis.breakingbad.core.repository.death

import com.harismexis.breakingbad.core.domain.Death

interface DeathsLocal {

    suspend fun save(items: List<Death>)

    suspend fun getDeaths(): List<Death>

}