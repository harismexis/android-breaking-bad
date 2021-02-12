package com.example.jsonfeed.instrumentedsetup.di.module

import android.content.Context

import com.example.jsonfeed.instrumentedsetup.application.InstrumentedMainApplication
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDao
import com.example.jsonfeed.framework.datasource.db.PokemonDatabase

import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedMainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): PokemonLocalDao {
        return PokemonDatabase.getDatabase(app.applicationContext).getLocalDao()
    }


}