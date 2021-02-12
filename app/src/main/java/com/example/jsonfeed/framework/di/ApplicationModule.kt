package com.example.jsonfeed.framework.di

import android.content.Context

import com.example.jsonfeed.framework.application.MainApplication
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDao
import com.example.jsonfeed.framework.datasource.db.PokemonDatabase

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): PokemonLocalDao {
        return PokemonDatabase.getDatabase(app.applicationContext).getLocalDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}