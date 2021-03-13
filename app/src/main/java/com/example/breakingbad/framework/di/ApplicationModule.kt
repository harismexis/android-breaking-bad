package com.example.breakingbad.framework.di

import android.content.Context
import com.example.breakingbad.framework.application.MainApplication
import com.example.breakingbad.framework.datasource.database.BreakingBadDatabase
import com.example.breakingbad.framework.datasource.database.BreakingBadLocalDao
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): BreakingBadLocalDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext).getDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}