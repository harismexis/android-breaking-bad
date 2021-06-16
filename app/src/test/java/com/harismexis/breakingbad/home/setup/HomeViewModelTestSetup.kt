package com.harismexis.breakingbad.home.setup

import androidx.lifecycle.Observer
import com.harismexis.breakingbad.framework.datasource.database.repository.ActorsLocalRepository
import com.harismexis.breakingbad.framework.datasource.network.repository.ActorsRemoteRepository
import com.harismexis.breakingbad.model.domain.Actor
import com.harismexis.breakingbad.model.result.ActorsResult
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
    protected lateinit var mockActorRemote: ActorsRemoteRepository
    @Mock
    protected lateinit var mockActorLocal: ActorsLocalRepository
    @Mock
    lateinit var mockObserver: Observer<ActorsResult>

    private val mockActors = actorsParser.getMockActorsWhenJsonHasAllItemsValid()
    private val mockActorsResultSuccess = ActorsResult.Success(mockActors)
    private val error = IllegalStateException(ERROR_MESSAGE)
    private val mockActorsResultError = ActorsResult.Error(error)

    protected lateinit var subject: HomeViewModel

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(mockActorRemote, mockActorLocal)
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
            Mockito.`when`(mockActorRemote.getActors(actorName)).thenReturn(items)
        }
    }

    protected fun mockRemoteActorsCallThrowsError(actorName: String? = null) {
        runBlocking {
            Mockito.`when`(mockActorRemote.getActors(actorName))
                .thenThrow(error)
        }
    }

    protected fun verifyRemoteActorsCallDone(name: String? = null) {
        runBlocking {
            verify(mockActorRemote, Mockito.times(1)).getActors(name)
        }
    }

    protected fun verifyRemoteActorsCallNotDone() {
        runBlocking {
            verify(mockActorRemote, Mockito.never()).getActors(any())
        }
    }

    // Local Call

    protected fun mockLocalActorsCallReturnsAllItemsValid() {
        mockLocalActorsCall(mockActors)
    }

    private fun mockLocalActorsCall(items: List<Actor>) {
        runBlocking {
            Mockito.`when`(mockActorLocal.getActors()).thenReturn(items)
        }
    }

    protected fun mockLocalActorsCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockActorLocal.getActors())
                .thenThrow(error)
        }
    }

    protected fun verifyLocalActorsCallDone() {
        runBlocking {
            verify(mockActorLocal, Mockito.times(1)).getActors()
        }
    }

    protected fun verifyLocalActorsCallNotDone() {
        runBlocking {
            verify(mockActorLocal, Mockito.never()).getActors()
        }
    }

    // LiveData

    protected fun observeActorsLiveData() {
        subject.actorsResult.observeForever(mockObserver)
    }

    protected fun stopObservingLiveData() {
        subject.actorsResult.removeObserver(mockObserver)
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
            verify(mockActorLocal, Mockito.times(1)).updateActors(items)
        }
    }

    protected fun verifyActorsNotStored() {
        runBlocking {
            verify(mockActorLocal, Mockito.never()).updateActors(any())
        }
    }

}