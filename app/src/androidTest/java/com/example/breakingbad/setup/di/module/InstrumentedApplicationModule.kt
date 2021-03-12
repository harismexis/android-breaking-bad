package com.example.breakingbad.setup.di.module

import android.content.Context
import com.example.breakingbad.framework.datasource.database.BBDatabase
import com.example.breakingbad.framework.datasource.database.BBLocalDao

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
    fun provideLocalDao(app: InstrumentedMainApplication): BBLocalDao {
        return BBDatabase.getDatabase(app.applicationContext).getDao()
    }


}