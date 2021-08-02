package com.harismexis.breakingbad.framework.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.breakingbad.framework.data.database.table.LocalDeath

@Dao
interface DeathsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LocalDeath>)

    @Query("SELECT * FROM deaths_table")
    suspend fun getAll(): List<LocalDeath?>?

    @Query("DELETE FROM deaths_table")
    suspend fun delete()

}
