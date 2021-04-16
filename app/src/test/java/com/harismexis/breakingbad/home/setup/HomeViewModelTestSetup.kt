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
    protected lateinit var mockIrrGetRemoteActors: IrrGetRemoteActors
    @Mock
    protected lateinit var mockIrrGetLocalActors: IrrGetLocalActors
    @Mock
    protected lateinit var mockIrrStoreActors: IrrStoreActors
    @Mock
    protected lateinit var mockHomeInteractors: HomeInteractors
    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitorSimple
    @Mock
    lateinit var mockObserver: Observer<ActorsResult>

    private val mockActors = actorsParser.getMockActorsWhenJsonHasAllItemsValid()
    private val mockActorsResultSuccess = ActorsResult.ActorsSuccess(mockActors)
    private val error = IllegalStateException(ERROR_MESSAGE)
    private val mockActorsResultError = ActorsResult.ActorsError(error)

    protected lateinit var subject: HomeViewModel

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(mockHomeInteractors, mockConnectivity)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockHomeInteractors.irrGetRemoteActors).thenReturn(mockIrrGetRemoteActors)
        Mockito.`when`(mockHomeInteractors.irrGetLocalActors).thenReturn(mockIrrGetLocalActors)
        Mockito.`when`(mockHomeInteractors.irrStoreActors).thenReturn(mockIrrStoreActors)
    }

    // Internet

    protected fun mockInternetActive(active: Boolean) {
        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
    }

    protected fun verifyInternetChecked() {
        verify(mockConnectivity, Mockito.times(1)).isOnline()
    }

    // Remote Call

    protected fun mockRemoteActorsCallReturnsAllItemsValid() {
        mockRemoteActorsCall(mockActors)
    }

    private fun mockRemoteActorsCall(
        items: List<Actor>,
        actorName: String? = null
    ) {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteActors(actorName)).thenReturn(items)
        }
    }

    protected fun mockRemoteActorsCallThrowsError(actorName: String? = null) {
        runBlocking {
            Mockito.`when`(mockIrrGetRemoteActors(actorName))
                .thenThrow(error)
        }
    }

    protected fun verifyRemoteActorsCallDone(name: String? = null) {
        runBlocking {
            verify(mockIrrGetRemoteActors, Mockito.times(1))(name)
        }
    }

    protected fun verifyRemoteActorsCallNotDone() {
        runBlocking {
            verify(mockIrrGetRemoteActors, Mockito.never())(any())
        }
    }

    // Local Call

    protected fun mockLocalActorsCallReturnsAllItemsValid() {
        mockLocalActorsCall(mockActors)
    }

    private fun mockLocalActorsCall(items: List<Actor>) {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalActors()).thenReturn(items)
        }
    }

    protected fun mockLocalActorsCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalActors())
                .thenThrow(error)
        }
    }

    protected fun verifyLocalActorsCallDone() {
        runBlocking {
            verify(mockIrrGetLocalActors, Mockito.times(1))()
        }
    }

    protected fun verifyLocalActorsCallNotDone() {
        runBlocking {
            verify(mockIrrGetLocalActors, Mockito.never())()
        }
    }

    // LiveData

    protected fun initActorsLiveData() {
        subject.actorsResult.observeForever(mockObserver)
    }

    protected fun verifyActorsLiveDataChangedWithSuccess() {
        verifyActorsLiveDataChanged(mockActorsResultSuccess)
    }

    protected fun verifyActorsLiveDataChangedWithError() {
        verifyActorsLiveDataChanged(mockActorsResultError)
    }

    private fun verifyActorsLiveDataChanged(result: ActorsResult) {
        verify(mockObserver).onChanged(result)
    }

    protected fun verifyActorsLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // Store Data

    protected fun verifyActorsStored() {
        verifyActorsStored(mockActors)
    }

    private fun verifyActorsStored(items: List<Actor>) {
        runBlocking {
            verify(mockIrrStoreActors, Mockito.times(1))(items)
        }
    }

    protected fun verifyActorsNotStored() {
        runBlocking {
            verify(mockIrrStoreActors, Mockito.never())(any())
        }
    }

}