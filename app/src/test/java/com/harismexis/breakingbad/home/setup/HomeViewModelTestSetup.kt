package com.harismexis.breakingbad.home.setup

import androidx.lifecycle.Observer
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActors
import com.harismexis.breakingbad.interactors.actor.IrrGetRemoteActors
import com.harismexis.breakingbad.interactors.actor.IrrStoreActors
import com.harismexis.breakingbad.presentation.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.home.interactors.HomeInteractors
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIrrGetLocalActors: IrrGetLocalActors

    @Mock
    protected lateinit var mockIrrGetRemoteActors: IrrGetRemoteActors

    @Mock
    protected lateinit var mockIrrStoreItems: IrrStoreActors

    @Mock
    protected lateinit var mockInteractors: HomeInteractors

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitorSimple

    @Mock
    lateinit var mockObserver: Observer<ActorsResult>

    private val mockItems = actorsParser.getMockActorsWhenJsonHasAllItemsValid()
    protected lateinit var subject: HomeViewModel
    private val mockActorsResultSuccess = ActorsResult.ActorsSuccess(mockItems)
    private val error = IllegalStateException(ERROR_MESSAGE)
    private val mockActorsResultError = ActorsResult.ActorsError(error)

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(mockInteractors, mockConnectivity)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.irrGetRemoteActors).thenReturn(mockIrrGetRemoteActors)
        Mockito.`when`(mockInteractors.irrGetLocalActors).thenReturn(mockIrrGetLocalActors)
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
            Mockito.`when`(mockIrrGetRemoteActors(actorName)).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError(actorName: String?) {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteActors(actorName))
                .thenThrow(error)
        }
    }

    protected fun verifyRemoteCallDone(name: String?) {
        runBlocking {
            verify(mockIrrGetRemoteActors, Mockito.times(1))(name)
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockIrrGetRemoteActors, Mockito.never())(any())
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<Actor>) {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalActors()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalActors())
                .thenThrow(error)
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockIrrGetLocalActors, Mockito.times(1))()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockIrrGetLocalActors, Mockito.never())()
        }
    }

    // LiveData

    protected fun initialiseLiveData() {
        subject.actorsResult.observeForever(mockObserver)
    }

    protected fun verifyLiveDataChangedWithSuccess() {
        verifyLiveDataChanged(mockActorsResultSuccess)
    }

    protected fun verifyLiveDataChangedWithError() {
        verifyLiveDataChanged(mockActorsResultError)
    }

    private fun verifyLiveDataChanged(result: ActorsResult) {
        verify(mockObserver).onChanged(result)
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
            verify(mockIrrStoreItems, Mockito.times(1))(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockIrrStoreItems, Mockito.never())(any())
        }
    }

}