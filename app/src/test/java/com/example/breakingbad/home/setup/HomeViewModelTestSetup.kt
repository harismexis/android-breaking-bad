package com.example.breakingbad.home.setup

import androidx.lifecycle.Observer
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.interactors.IrrGetLocalBBActors
import com.example.breakingbad.interactors.IrrGetRemoteBBActors
import com.example.breakingbad.interactors.IrrStoreBBActors
import com.example.breakingbad.presentation.home.viewmodel.HomeViewModel
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIrrGetLocalItems: IrrGetLocalBBActors

    @Mock
    protected lateinit var mockIrrGetRemoteItems: IrrGetRemoteBBActors

    @Mock
    protected lateinit var mockIrrStoreItems: IrrStoreBBActors

    @Mock
    protected lateinit var mockInteractors: HomeInteractors

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitorSimple

    @Mock
    lateinit var mockObserver: Observer<List<BBActor>>

    private val mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
    protected lateinit var subject: HomeViewModel

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(mockInteractors, mockConnectivity)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.irrGetRemoteBBActors).thenReturn(mockIrrGetRemoteItems)
        Mockito.`when`(mockInteractors.irrGetLocalBBActors).thenReturn(mockIrrGetLocalItems)
        Mockito.`when`(mockInteractors.irrStoreBBActors).thenReturn(mockIrrStoreItems)
    }

    // Internet

    protected fun mockInternetOn() {
        mockInternetActive(true)
    }

    protected fun mockInternetOff() {
        mockInternetActive(false)
    }

    private fun mockInternetActive(active: Boolean) {
        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
    }

    protected fun verifyInternetChecked() {
        verify(mockConnectivity, Mockito.times(1)).isOnline()
    }

    // Remote Call

    protected fun mockRemoteCallReturnsAllItemsValid() {
        mockRemoteCall(mockItems)
    }

    private fun mockRemoteCall(items: List<BBActor>) {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyRemoteCallDone() {
        runBlocking {
            verify(mockIrrGetRemoteItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockIrrGetRemoteItems, Mockito.never()).invoke()
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<BBActor>) {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockIrrGetLocalItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockIrrGetLocalItems, Mockito.never()).invoke()
        }
    }

    // LiveData

    protected fun initialiseLiveData() {
        subject.models.observeForever(mockObserver)
    }

    protected fun verifyLiveDataChangedAsExpected() {
        verifyLiveDataChanged(mockItems)
    }

    private fun verifyLiveDataChanged(items: List<BBActor>) {
        verify(mockObserver).onChanged(items)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // Store Data

    protected fun verifyDataStored() {
        verifyDataStored(mockItems)
    }

    private fun verifyDataStored(items: List<BBActor>) {
        runBlocking {
            verify(mockIrrStoreItems, Mockito.times(1)).invoke(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockIrrStoreItems, Mockito.never()).invoke(any())
        }
    }

}