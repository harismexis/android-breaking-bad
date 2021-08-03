package com.harismexis.breakingbad.framework.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.breakingbad.framework.data.database.table.LocalActor

@Dao
interface ActorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LocalActor>)

    @Query("SELECT * FROM actors_table WHERE id = :actorId")
    suspend fun getActorById(actorId: Int): LocalActor?

    @Query("SELECT * FROM actors_table")
    suspend fun getAll(): List<LocalActor?>?

    @Query("DELETE FROM actors_table")
    suspend fun delete()

}
