package com.example.breakingbad.framework.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BBActorLocal::class], version = 1, exportSchema = false)
abstract class BBDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: BBDatabase? = null
        private const val DATABASE_FILE_NAME = "breaking_bad_room_database"

        fun getDatabase(
            context: Context
        ): BBDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BBDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): BBLocalDao

}
