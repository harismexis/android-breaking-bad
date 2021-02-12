package com.example.jsonfeed.shared

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteItems
import com.example.jsonfeed.interactors.StoreItems
import org.mockito.Mock

abstract class VmTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIRRGetLocalItem: GetLocalItem
    @Mock
    protected lateinit var mockIRRGetLocalItems: GetLocalItems
    @Mock
    protected lateinit var mockIRRGetRemoteItems: GetRemoteItems
    @Mock
    protected lateinit var mockIRRStoreItems: StoreItems
    @Mock
    protected lateinit var mockInteractors: Interactors

}