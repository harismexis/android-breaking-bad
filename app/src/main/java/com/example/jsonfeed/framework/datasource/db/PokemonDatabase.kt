package com.example.jsonfeed.framework.datasource.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: PokemonDatabase? = null
        private const val DATABASE_FILE_NAME = "pokemon_database"

        fun getDatabase(
            context: Context
        ): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getLocalDao(): PokemonLocalDao

}
