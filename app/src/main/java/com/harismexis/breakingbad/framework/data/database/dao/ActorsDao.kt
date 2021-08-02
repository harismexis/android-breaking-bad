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

    @Query("SELECT * FROM breaking_bad_actor_table WHERE char_id = :itemId")
    suspend fun getById(itemId: Int): LocalActor?

    @Query("SELECT * FROM breaking_bad_actor_table")
    suspend fun getAll(): List<LocalActor?>?

    @Query("DELETE FROM breaking_bad_actor_table")
    suspend fun delete()

}
