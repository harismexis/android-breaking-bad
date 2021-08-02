package com.harismexis.breakingbad.framework.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.breakingbad.framework.data.database.table.LocalQuote

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LocalQuote>)

    @Query("SELECT * FROM quote_table")
    suspend fun getAll(): List<LocalQuote?>?

    @Query("SELECT * FROM quote_table WHERE series = :seriesName")
    suspend fun getBySeries(seriesName: String?): List<LocalQuote?>?

    @Query("DELETE FROM quote_table")
    suspend fun delete()

}
