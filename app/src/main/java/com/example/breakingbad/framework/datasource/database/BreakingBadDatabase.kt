package com.example.breakingbad.framework.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalActor::class, LocalQuote::class],
    version = 1,
    exportSchema = false
)
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
