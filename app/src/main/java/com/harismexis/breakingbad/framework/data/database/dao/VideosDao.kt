package com.harismexis.breakingbad.framework.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.breakingbad.framework.data.database.table.LocalVideo

@Dao
interface VideosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LocalVideo>)

    @Query("SELECT * FROM videos_table WHERE id = :itemId")
    suspend fun getById(itemId: Int): LocalVideo?

    @Query("SELECT * FROM videos_table")
    suspend fun getAll(): List<LocalVideo?>?

    @Query("DELETE FROM videos_table")
    suspend fun delete()

}
