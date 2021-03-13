package com.example.breakingbad.framework.datasource.database

import androidx.room.*

@Dao
interface BBLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<BBActorLocal>)

    @Query("SELECT * FROM breaking_bad_actor_table WHERE char_id = :itemId")
    suspend fun getItemById(itemId: Int): BBActorLocal?

    @Query("SELECT * FROM breaking_bad_actor_table")
    suspend fun getAllItems(): List<BBActorLocal?>?

    @Query("DELETE FROM breaking_bad_actor_table")
    suspend fun deleteAll()

}
