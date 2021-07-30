package com.harismexis.breakingbad.framework.di

import com.harismexis.breakingbad.framework.application.MainApplication
import com.harismexis.breakingbad.framework.datasource.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.datasource.database.schema.BreakingBadDatabase
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): BreakingBadLocalDao {
        return BreakingBadDatabase.getDatabase(app.applicationContext).getDao()
    }

}