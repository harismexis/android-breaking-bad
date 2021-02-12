package com.example.jsonfeed.di.module

import android.content.Context

import com.example.jsonfeed.application.InstrumentedMainApplication
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