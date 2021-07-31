package com.harismexis.breakingbad.core.repository.death

import com.harismexis.breakingbad.core.domain.Death


interface DeathsRemote {

    suspend fun getDeaths(): List<Death>

}