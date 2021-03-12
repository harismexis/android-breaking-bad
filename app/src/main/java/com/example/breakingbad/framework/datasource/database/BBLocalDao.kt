package com.example.breakingbad.framework.datasource.database

import androidx.room.*

@Dao
interface BBLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<BBCharacterLocal>)

    @Query("SELECT * FROM breaking_bad_character_table WHERE char_id = :itemId")
    suspend fun getItemById(itemId: Int): BBCharacterLocal?

    @Query("SELECT * FROM breaking_bad_character_table")
    suspend fun getAllItems(): List<BBCharacterLocal?>?

    @Query("DELETE FROM breaking_bad_character_table")
    suspend fun deleteAll()

}
