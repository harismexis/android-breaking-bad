package com.harismexis.breakingbad.setup.di.module

import android.content.Context
import com.harismexis.breakingbad.framework.datasource.database.schema.BreakingBadDatabase
import com.harismexis.breakingbad.framework.datasource.database.dao.BreakingBadLocalDao

import com.harismexis.breakingbad.setup.application.InstrumentedApplication

import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedApplication): BreakingBadLocalDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext).getDao()
    }


}