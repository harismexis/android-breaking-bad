package com.harismexis.breakingbad.framework.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
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
        LocalEpisode::class,
        LocalVideo::class
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
                    .addCallback(DatabaseCallback(scope))
                    .build()
                this.instance = instance
                instance
            }
        }
    }

    private class DatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    // TODO: Insert videos
                    // val list = mutableListOf<LocalVideo>()
                    // instance?.getVideosDao().insert(list)
                }
            }
        }
    }

    abstract fun getActorsDao(): ActorsDao

    abstract fun getEpisodesDao(): EpisodesDao

    abstract fun getQuotesDao(): QuotesDao

    abstract fun getDeathsDao(): DeathsDao

    abstract fun getVideosDao(): VideosDao
}
