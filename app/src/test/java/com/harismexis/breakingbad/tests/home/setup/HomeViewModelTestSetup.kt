package com.harismexis.breakingbad.tests.home.setup

import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.harismexis.breakingbad.base.BaseUnitTest
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.result.ActorsResult
import com.harismexis.breakingbad.framework.data.database.repository.ActorsLocalRepository
import com.harismexis.breakingbad.framework.data.network.repository.ActorsRemoteRepository
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeViewModelTestSetup : BaseUnitTest() {

    @Mock
    protected lateinit var mockActorRemote: ActorsRemoteRepository
    @Mock
    protected lateinit var mockActorLocal: ActorsLocalRepository
    @Mock
    protected lateinit var mockStateHandle: SavedStateHandle
    @Mock
    lateinit var mockObserver: Observer<ActorsResult>

    private val mockActors = mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid()
    private val mockActorsResultSuccess = ActorsResult.Success(mockActors)
    private val error = IllegalStateException(ERROR_MESSAGE)
    private val mockActorsResultError = ActorsResult.Error(error)

    protected lateinit var subject: HomeViewModel

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    protected fun initSubject() {
        subject = HomeViewModel(mockActorRemote, mockActorLocal, mockStateHandle)
    }

    // Remote Call

    protected fun mockRemoteActorsCallSuccess() {
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

    protected fun mockRemoteActorsCallFailed() {
        runBlocking {
            Mockito.`when`(mockActorRemote.getActors(any()))
                .thenThrow(error)
        }
    }

    protected fun verifyRemoteActorsCallDone(name: String? = null) {
        runBlocking {
            verify(mockActorRemote, Mockito.times(1)).getActors(name)
        }
    }

    // Local Call

    protected fun mockLocalActorsCallSuccess() {
        mockLocalActorsCall(mockActors)
    }

    private fun mockLocalActorsCall(items: List<Actor>) {
        runBlocking {
            Mockito.`when`(mockActorLocal.getActors()).thenReturn(items)
        }
    }

    protected fun mockLocalActorsCallFailed() {
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

    protected fun observeLiveData() {
        subject.actors.observeForever(mockObserver)
    }

    protected fun stopObserveLiveData() {
        subject.actors.removeObserver(mockObserver)
    }

    protected fun verifyLiveDataEmitSuccess() {
        verifyActorsLiveDataChanged(mockActorsResultSuccess)
    }

    protected fun verifyLiveDataEmitError() {
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
            verify(mockActorLocal, Mockito.times(1)).save(items)
        }
    }

    protected fun verifyActorsNotStored() {
        runBlocking {
            verify(mockActorLocal, Mockito.never()).save(any())
        }
    }

}