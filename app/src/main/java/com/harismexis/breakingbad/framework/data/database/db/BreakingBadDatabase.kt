package com.harismexis.breakingbad.framework.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.framework.data.database.converter.Converter
import com.harismexis.breakingbad.framework.data.database.dao.*
import com.harismexis.breakingbad.framework.data.database.table.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        var instance: BreakingBadDatabase? = null
        private const val DATABASE_FILE_NAME = "breaking_bad_room_database"

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BreakingBadDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BreakingBadDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                this.instance = instance
                instance
            }
        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**
         * Override the onCreate method to populate the database.
         */
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            instance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    val list = mutableListOf<LocalActor>()
                    val actor = Actor(
                        1,
                        "haris",
                        "12/4/56",
                        mutableListOf(),
                        "",
                        "alive",
                        "freak",
                        "john cage",
                        "breaking bad"
                    ).toLocalItem()
                    list.add(actor)
                    //instance?.getActorsDao()?.insert(list)
                }
            }
        }
    }

   // abstract fun getDao(): BreakingBadLocalDao

    abstract fun getActorsDao(): ActorsDao

    abstract fun getEpisodesDao(): EpisodesDao

    abstract fun getQuotesDao(): QuotesDao

    abstract fun getDeathsDao(): DeathsDao

    abstract fun getVideosDao(): VideosDao

}
