package com.harismexis.breakingbad.framework.datasource.database.data

import androidx.room.*
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import com.harismexis.breakingbad.framework.datasource.database.table.LocalDeath
import com.harismexis.breakingbad.framework.datasource.database.table.LocalEpisode
import com.harismexis.breakingbad.framework.datasource.database.table.LocalQuote

@Dao
interface BreakingBadLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(items: List<LocalActor>)

    @Query("SELECT * FROM breaking_bad_actor_table WHERE char_id = :itemId")
    suspend fun getActorById(itemId: Int): LocalActor?

    @Query("SELECT * FROM breaking_bad_actor_table")
    suspend fun getAllActors(): List<LocalActor?>?

    @Query("DELETE FROM breaking_bad_actor_table")
    suspend fun deleteAllActors()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(items: List<LocalQuote>)

    @Query("SELECT * FROM quote_table")
    suspend fun getAllQuotes(): List<LocalQuote?>?

    @Query("DELETE FROM quote_table")
    suspend fun deleteAllQuotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeaths(items: List<LocalDeath>)

    @Query("SELECT * FROM death_table")
    suspend fun getAllDeaths(): List<LocalDeath?>?

    @Query("DELETE FROM death_table")
    suspend fun deleteAllDeaths()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(items: List<LocalEpisode>)

    @Query("SELECT * FROM episode_table")
    suspend fun getAllEpisodes(): List<LocalEpisode?>?

    @Query("DELETE FROM episode_table")
    suspend fun deleteAllEpisodes()

}
