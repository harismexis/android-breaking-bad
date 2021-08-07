package com.harismexis.breakingbad.framework.di.module

import com.harismexis.breakingbad.framework.application.MainApplication
import com.harismexis.breakingbad.framework.data.database.dao.*
import com.harismexis.breakingbad.framework.data.database.db.BreakingBadDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideBreakingBadDatabase(app: MainApplication): BreakingBadDatabase {
        return BreakingBadDatabase.getDatabase(app.applicationContext, app.appScope)
    }

    @Singleton
    @Provides
    fun provideActorsDao(db: BreakingBadDatabase): ActorsDao {
        return db.getActorsDao()
    }

    @Singleton
    @Provides
    fun provideQuotesDao(db: BreakingBadDatabase): QuotesDao {
        return db.getQuotesDao()
    }

    @Provides
    @Singleton
    fun provideEpisodesDao(db: BreakingBadDatabase): EpisodesDao {
        return db.getEpisodesDao()
    }

    @Singleton
    @Provides
    fun provideDeathsDao(db: BreakingBadDatabase): DeathsDao {
        return db.getDeathsDao()
    }

    @Singleton
    @Provides
    fun provideVideosDao(db: BreakingBadDatabase): VideosDao {
        return db.getVideosDao()
    }

}