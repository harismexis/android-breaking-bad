package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.database.BBCharacterLocalDataSource
import com.example.breakingbad.data.BBCharacterLocalRepository
import com.example.breakingbad.data.BBCharacterRemoteRepository
import com.example.breakingbad.presentation.interactors.BBCharacterDetailInteractors
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.datasource.network.data.BBCharacterRemoteDataSource
import com.example.breakingbad.interactors.InterGetLocalBBCharacter
import com.example.breakingbad.interactors.InterGetLocalBBCharacters
import com.example.breakingbad.interactors.InterGetRemoteBBCharacters
import com.example.breakingbad.interactors.InterStoreBBCharacters
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideHomeInteractors(
        interGetLocalBBCharacters: InterGetLocalBBCharacters,
        interGetRemoteBBCharacters: InterGetRemoteBBCharacters,
        interStoreBBCharacters: InterStoreBBCharacters
    ): HomeInteractors {
        return HomeInteractors(
            interGetLocalBBCharacters,
            interGetRemoteBBCharacters,
            interStoreBBCharacters
        )
    }

    @Provides
    fun provideBBCharacterDetailInteractors(
        interGetLocalBBCharacter: InterGetLocalBBCharacter
    ): BBCharacterDetailInteractors {
        return BBCharacterDetailInteractors(interGetLocalBBCharacter)
    }

    @Provides
    fun provideInteractorGetLocalBBCharacter(
        dataSource: BBCharacterLocalDataSource
    ): InterGetLocalBBCharacter {
        return InterGetLocalBBCharacter(BBCharacterLocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetLocalBBCharacters(
        dataSource: BBCharacterLocalDataSource
    ): InterGetLocalBBCharacters {
        return InterGetLocalBBCharacters(BBCharacterLocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetRemoteItems(
        dataSource: BBCharacterRemoteDataSource
    ): InterGetRemoteBBCharacters {
        return InterGetRemoteBBCharacters(BBCharacterRemoteRepository(dataSource))
    }

    @Provides
    fun provideInteractorInsertBBCharacters(
        dataSource: BBCharacterLocalDataSource
    ): InterStoreBBCharacters {
        return InterStoreBBCharacters(BBCharacterLocalRepository(dataSource))
    }

}