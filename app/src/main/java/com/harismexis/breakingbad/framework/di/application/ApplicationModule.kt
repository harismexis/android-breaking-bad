package com.harismexis.breakingbad.framework.di.application

import android.content.Context
import com.harismexis.breakingbad.framework.application.MainApplication
import com.harismexis.breakingbad.model.datasource.database.schema.BreakingBadDatabase
import com.harismexis.breakingbad.model.datasource.database.dao.BreakingBadLocalDao
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