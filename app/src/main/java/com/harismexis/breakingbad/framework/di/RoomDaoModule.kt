package com.harismexis.breakingbad.framework.di

import com.harismexis.breakingbad.framework.application.MainApplication
import com.harismexis.breakingbad.framework.data.database.dao.*
import com.harismexis.breakingbad.framework.data.database.db.BreakingBadDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDaoModule {

    @Provides
    @Singleton
    fun provideActorsDao(app: MainApplication): ActorsDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext, app.appScope)
            .getActorsDao()
    }

    @Provides
    @Singleton
    fun provideQuotesDao(app: MainApplication): QuotesDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext, app.appScope)
            .getQuotesDao()
    }

    @Provides
    @Singleton
    fun provideEpisodesDao(app: MainApplication): EpisodesDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext, app.appScope)
            .getEpisodesDao()
    }

    @Provides
    @Singleton
    fun provideDeathsDao(app: MainApplication): DeathsDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext, app.appScope)
            .getDeathsDao()
    }

    @Provides
    @Singleton
    fun provideVideosDao(app: MainApplication): VideosDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext, app.appScope)
            .getVideosDao()
    }


}