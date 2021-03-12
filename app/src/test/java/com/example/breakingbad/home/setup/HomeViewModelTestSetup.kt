package com.example.breakingbad.home.setup

import androidx.lifecycle.Observer
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.util.network.ConnectivityMonitor
import com.example.breakingbad.interactors.InterGetLocalBBCharacters
import com.example.breakingbad.interactors.InterGetRemoteBBCharacters
import com.example.breakingbad.interactors.InterStoreBBCharacters
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
    protected lateinit var mockInterGetLocalItems: InterGetLocalBBCharacters

    @Mock
    protected lateinit var mockInterGetRemoteItems: InterGetRemoteBBCharacters

    @Mock
    protected lateinit var mockInterStoreItems: InterStoreBBCharacters

    @Mock
    protected lateinit var mockInteractors: HomeInteractors

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    @Mock
    lateinit var mockObserver: Observer<List<BBCharacter>>

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
        Mockito.`when`(mockInteractors.interGetRemoteBBCharacters).thenReturn(mockInterGetRemoteItems)
        Mockito.`when`(mockInteractors.interGetLocalBBCharacters).thenReturn(mockInterGetLocalItems)
        Mockito.`when`(mockInteractors.interStoreBBCharacters).thenReturn(mockInterStoreItems)
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

    private fun mockRemoteCall(items: List<BBCharacter>) {
        runBlocking {
            Mockito.`when`(mockInterGetRemoteItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInterGetRemoteItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyRemoteCallDone() {
        runBlocking {
            verify(mockInterGetRemoteItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockInterGetRemoteItems, Mockito.never()).invoke()
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<BBCharacter>) {
        runBlocking {
            Mockito.`when`(mockInterGetLocalItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInterGetLocalItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockInterGetLocalItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockInterGetLocalItems, Mockito.never()).invoke()
        }
    }

    // LiveData

    protected fun initialiseLiveData() {
        subject.models.observeForever(mockObserver)
    }

    protected fun verifyLiveDataChangedAsExpected() {
        verifyLiveDataChanged(mockItems)
    }

    private fun verifyLiveDataChanged(items: List<BBCharacter>) {
        verify(mockObserver).onChanged(items)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // Store Data

    protected fun verifyDataStored() {
        verifyDataStored(mockItems)
    }

    private fun verifyDataStored(items: List<BBCharacter>) {
        runBlocking {
            verify(mockInterStoreItems, Mockito.times(1)).invoke(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockInterStoreItems, Mockito.never()).invoke(any())
        }
    }

}