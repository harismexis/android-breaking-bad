package com.harismexis.breakingbad.home.setup

import androidx.lifecycle.Observer
import com.harismexis.breakingbad.datamodel.data.LocalRepository
import com.harismexis.breakingbad.datamodel.data.RemoteRepository
import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.ActorsResult
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
    protected lateinit var mockRemoteRepo: RemoteRepository
    @Mock
    protected lateinit var mockLocalRepo: LocalRepository

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

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(mockRemoteRepo, mockLocalRepo, mockConnectivity)
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
            Mockito.`when`(mockRemoteRepo.getActors(actorName)).thenReturn(items)
        }
    }

    protected fun mockRemoteActorsCallThrowsError(actorName: String? = null) {
        runBlocking {
            Mockito.`when`(mockRemoteRepo.getActors(actorName))
                .thenThrow(error)
        }
    }

    protected fun verifyRemoteActorsCallDone(name: String? = null) {
        runBlocking {
            verify(mockRemoteRepo, Mockito.times(1)).getActors(name)
        }
    }

    protected fun verifyRemoteActorsCallNotDone() {
        runBlocking {
            verify(mockRemoteRepo, Mockito.never()).getActors(any())
        }
    }

    // Local Call

    protected fun mockLocalActorsCallReturnsAllItemsValid() {
        mockLocalActorsCall(mockActors)
    }

    private fun mockLocalActorsCall(items: List<Actor>) {
        runBlocking {
            Mockito.`when`(mockLocalRepo.getActors()).thenReturn(items)
        }
    }

    protected fun mockLocalActorsCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockLocalRepo.getActors())
                .thenThrow(error)
        }
    }

    protected fun verifyLocalActorsCallDone() {
        runBlocking {
            verify(mockLocalRepo, Mockito.times(1)).getActors()
        }
    }

    protected fun verifyLocalActorsCallNotDone() {
        runBlocking {
            verify(mockLocalRepo, Mockito.never()).getActors()
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
            verify(mockLocalRepo, Mockito.times(1)).updateActors(items)
        }
    }

    protected fun verifyActorsNotStored() {
        runBlocking {
            verify(mockLocalRepo, Mockito.never()).updateActors(any())
        }
    }

}