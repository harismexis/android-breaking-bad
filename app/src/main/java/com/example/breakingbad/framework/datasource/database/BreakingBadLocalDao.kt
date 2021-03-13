package com.example.breakingbad.framework.datasource.database

import androidx.room.*

@Dao
interface BreakingBadLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalActor>)

    @Query("SELECT * FROM breaking_bad_actor_table WHERE char_id = :itemId")
    suspend fun getItemById(itemId: Int): LocalActor?

    @Query("SELECT * FROM breaking_bad_actor_table")
    suspend fun getAllItems(): List<LocalActor?>?

    @Query("DELETE FROM breaking_bad_actor_table")
    suspend fun deleteAll()

}
