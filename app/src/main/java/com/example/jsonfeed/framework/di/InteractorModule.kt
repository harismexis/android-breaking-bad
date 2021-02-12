package com.example.jsonfeed.framework.di

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.data.RemoteRepository

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDataSource
import com.example.jsonfeed.framework.datasource.network.PokemonRemoteDataSource

import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteItems
import com.example.jsonfeed.interactors.StoreItems

import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractors(
        getLocalFeedItem: GetLocalItem,
        getLocalFeedItems: GetLocalItems,
        getRemoteFeed: GetRemoteItems,
        insertLocalFeedItems: StoreItems
    ): Interactors {
        return Interactors(
            getLocalFeedItem,
            getLocalFeedItems,
            getRemoteFeed,
            insertLocalFeedItems
        )
    }

    @Provides
    fun provideInteractorGetLocalFeedItem(
        dataSource: PokemonLocalDataSource
    ): GetLocalItem {
        return GetLocalItem(LocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetLocalFeedItems(
        dataSource: PokemonLocalDataSource
    ): GetLocalItems {
        return GetLocalItems(LocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorInsertLocalFeedItems(
        dataSource: PokemonLocalDataSource
    ): StoreItems {
        return StoreItems(LocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetRemoteFeed(
        dataSource: PokemonRemoteDataSource
    ): GetRemoteItems {
        return GetRemoteItems(RemoteRepository(dataSource))
    }

}