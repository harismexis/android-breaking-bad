package com.example.breakingbad.home.setup

import androidx.lifecycle.Observer
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.presentation.screens.home.interactors.HomeInteractors
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.interactors.actor.IrrGetLocalActors
import com.example.breakingbad.interactors.actor.IrrGetRemoteActorsByName
import com.example.breakingbad.interactors.actor.IrrStoreActors
import com.example.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIrrGetLocalItems: IrrGetLocalActors

    @Mock
    protected lateinit var mockIrrGetRemoteItemsByName: IrrGetRemoteActorsByName

    @Mock
    protected lateinit var mockIrrStoreItems: IrrStoreActors

    @Mock
    protected lateinit var mockInteractors: HomeInteractors

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitorSimple

    @Mock
    lateinit var mockObserver: Observer<List<Actor>>

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
        Mockito.`when`(mockInteractors.irrGetRemoteActorsByName).thenReturn(mockIrrGetRemoteItemsByName)
        Mockito.`when`(mockInteractors.irrGetLocalActors).thenReturn(mockIrrGetLocalItems)
        Mockito.`when`(mockInteractors.irrStoreActors).thenReturn(mockIrrStoreItems)
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
        mockRemoteCall(mockItems, null)
    }

    private fun mockRemoteCall(
        items: List<Actor>,
        actorName: String?
    ) {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteItemsByName.invoke(actorName)).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError(actorName: String?) {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteItemsByName.invoke(actorName))
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyRemoteCallDone(name: String?) {
        runBlocking {
            verify(mockIrrGetRemoteItemsByName, Mockito.times(1)).invoke(name)
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockIrrGetRemoteItemsByName, Mockito.never()).invoke(any())
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<Actor>) {
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

    private fun verifyLiveDataChanged(items: List<Actor>) {
        verify(mockObserver).onChanged(items)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // Store Data

    protected fun verifyDataStored() {
        verifyDataStored(mockItems)
    }

    private fun verifyDataStored(items: List<Actor>) {
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