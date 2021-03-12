package com.example.breakingbad.framework.datasource.database

import androidx.room.*

@Dao
interface BBLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<BBCharacterLocalItem>)

    @Query("SELECT * FROM breaking_bad_character_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): BBCharacterLocalItem?

    @Query("SELECT * FROM breaking_bad_character_table")
    suspend fun getAllItems(): List<BBCharacterLocalItem?>?

    @Query("DELETE FROM breaking_bad_character_table")
    suspend fun deleteAll()

}
