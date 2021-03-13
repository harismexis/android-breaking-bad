package com.example.breakingbad.setup.di.module

import android.content.Context
import com.example.breakingbad.framework.datasource.database.BreakingBadDatabase
import com.example.breakingbad.framework.datasource.database.BreakingBadLocalDao

import com.example.breakingbad.setup.application.InstrumentedMainApplication

import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedMainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): BreakingBadLocalDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext).getDao()
    }


}