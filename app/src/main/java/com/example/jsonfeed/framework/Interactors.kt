package com.example.jsonfeed.framework

import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteItems
import com.example.jsonfeed.interactors.StoreItems

data class Interactors(
    val getLocalItem: GetLocalItem,
    val getLocalItems: GetLocalItems,
    val getRemoteItems: GetRemoteItems,
    val storeItems: StoreItems
)
