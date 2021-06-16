package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Death


interface DeathsRemote {

    suspend fun getDeaths(): List<Death>

}