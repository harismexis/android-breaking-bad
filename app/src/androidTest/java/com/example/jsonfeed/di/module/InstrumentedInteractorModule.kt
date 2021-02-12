package com.example.jsonfeed.di.module

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteItems
import com.example.jsonfeed.interactors.StoreItems

import dagger.Module
import dagger.Provides

import io.mockk.mockk

@Module
class InstrumentedInteractorModule {

    @Provides
    fun provideInteractors(
        getLocalFeedItem: GetLocalItem,
        getLocalFeedItems: GetLocalItems,
        getRemoteFeed: GetRemoteItems,
        insertLocalFeedItems: StoreItems
    ): Interactors {
        return mockk(relaxed = true)
    }

}