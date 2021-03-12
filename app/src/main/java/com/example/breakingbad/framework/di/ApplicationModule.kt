package com.example.breakingbad.framework.di

import android.content.Context
import com.example.breakingbad.framework.application.MainApplication
import com.example.breakingbad.framework.datasource.database.BBDatabase
import com.example.breakingbad.framework.datasource.database.BBLocalDao
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): BBLocalDao {
        return BBDatabase.getDatabase(app.applicationContext).getDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}