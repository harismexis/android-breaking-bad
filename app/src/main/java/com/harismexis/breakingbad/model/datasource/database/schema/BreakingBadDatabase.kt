package com.harismexis.breakingbad.model.datasource.database.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harismexis.breakingbad.model.datasource.database.converter.Converter
import com.harismexis.breakingbad.model.datasource.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.model.datasource.database.table.LocalActor
import com.harismexis.breakingbad.model.datasource.database.table.LocalDeath
import com.harismexis.breakingbad.model.datasource.database.table.LocalEpisode
import com.harismexis.breakingbad.model.datasource.database.table.LocalQuote

@Database(
    entities = [
        LocalActor::class,
        LocalQuote::class,
        LocalDeath::class,
        LocalEpisode::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class BreakingBadDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: BreakingBadDatabase? = null
        private const val DATABASE_FILE_NAME = "breaking_bad_room_database"

        fun getDatabase(
            context: Context
        ): BreakingBadDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BreakingBadDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): BreakingBadLocalDao

}
