package com.harismexis.breakingbad.framework.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.breakingbad.framework.data.database.table.LocalEpisode

@Dao
interface EpisodesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LocalEpisode>)

    @Query("SELECT * FROM episodes_table")
    suspend fun getAll(): List<LocalEpisode?>?

    @Query("DELETE FROM episodes_table")
    suspend fun delete()

}
