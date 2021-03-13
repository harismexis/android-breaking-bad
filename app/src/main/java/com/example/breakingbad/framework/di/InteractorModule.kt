package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.database.BBCharacterLocalDataSource
import com.example.breakingbad.data.BBCharacterLocalRepository
import com.example.breakingbad.data.BBCharacterRemoteRepository
import com.example.breakingbad.presentation.interactors.BBCharacterDetailInteractors
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.datasource.network.data.BBCharacterRemoteDataSource
import com.example.breakingbad.interactors.*
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideHomeInteractors(
        irrGetLocalBBCharacters: InterGetLocalBBCharacters,
        irrGetRemoteBBCharacters: InterGetRemoteBBCharacters,
        irrGetRemoteBBCharactersByName: InterGetRemoteBBCharactersByName,
        irrStoreBBCharacters: InterStoreBBCharacters
    ): HomeInteractors {
        return HomeInteractors(
            irrGetLocalBBCharacters,
            irrGetRemoteBBCharacters,
            irrGetRemoteBBCharactersByName,
            irrStoreBBCharacters
        )
    }

    @Provides
    fun provideBBCharacterDetailInteractors(
        interGetLocalBBCharacter: InterGetLocalBBCharacter
    ): BBCharacterDetailInteractors {
        return BBCharacterDetailInteractors(interGetLocalBBCharacter)
    }

    @Provides
    fun provideIRRGetRemoteItems(
        dataSource: BBCharacterRemoteDataSource
    ): InterGetRemoteBBCharacters {
        return InterGetRemoteBBCharacters(BBCharacterRemoteRepository(dataSource))
    }

    @Provides
    fun provideIRRGetRemoteItemsByName(
        dataSource: BBCharacterRemoteDataSource
    ): InterGetRemoteBBCharactersByName {
        return InterGetRemoteBBCharactersByName(BBCharacterRemoteRepository(dataSource))
    }

    @Provides
    fun provideIRRGetLocalBBCharacters(
        dataSource: BBCharacterLocalDataSource
    ): InterGetLocalBBCharacters {
        return InterGetLocalBBCharacters(BBCharacterLocalRepository(dataSource))
    }

    @Provides
    fun provideIRRGetLocalBBCharacter(
        dataSource: BBCharacterLocalDataSource
    ): InterGetLocalBBCharacter {
        return InterGetLocalBBCharacter(BBCharacterLocalRepository(dataSource))
    }

    @Provides
    fun provideIRRInsertBBCharacters(
        dataSource: BBCharacterLocalDataSource
    ): InterStoreBBCharacters {
        return InterStoreBBCharacters(BBCharacterLocalRepository(dataSource))
    }

}