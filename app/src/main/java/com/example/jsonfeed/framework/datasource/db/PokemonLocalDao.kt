package com.example.jsonfeed.framework.datasource.db

import androidx.room.*

@Dao
interface PokemonLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): PokemonEntity?

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllItems(): List<PokemonEntity?>?

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()

}
