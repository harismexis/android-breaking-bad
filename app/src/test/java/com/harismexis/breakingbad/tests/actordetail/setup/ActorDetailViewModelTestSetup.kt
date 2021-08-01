package com.harismexis.breakingbad.tests.actordetail.setup

import androidx.lifecycle.Observer
import com.harismexis.breakingbad.base.BaseUnitTest
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.result.ActorDetailResult
import com.harismexis.breakingbad.framework.data.database.repository.ActorsLocalRepository
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class ActorDetailViewModelTestSetup : BaseUnitTest() {

    @Mock
    protected lateinit var mockActorLocal: ActorsLocalRepository
    @Mock
    lateinit var mockObserver: Observer<ActorDetailResult>

    private lateinit var mockActor: Actor
    protected var mockActorId: Int = 0
    private lateinit var mockActorDetailSuccess: ActorDetailResult
    private lateinit var mockActorDetailError: ActorDetailResult

    protected lateinit var subject: ActorDetailViewModel

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    protected fun initSubject() {
        mockActor = mockActorsProvider.getMockActor()
        mockActorId = mockActor.actorId
        subject = ActorDetailViewModel(mockActorLocal)
        mockActorDetailSuccess = ActorDetailResult.Success(mockActor)
        mockActorDetailError = ActorDetailResult.Error(ERROR_MESSAGE)
    }

    // Local Call

    protected fun mockLocalActorCallSuccess() {
        mockLocalActorCall(mockActorId, mockActor)
    }

    private fun mockLocalActorCall(
        actorId: Int,
        actor: Actor
    ) {
        runBlocking {
            Mockito.`when`(mockActorLocal.getActor(actorId)).thenReturn(actor)
        }
    }

    protected fun mockLocalActorCallFailed() {
        runBlocking {
            Mockito.`when`(mockActorLocal.getActor(any()))
                .thenThrow(IllegalStateException(ERROR_MESSAGE))
        }
    }

    protected fun verifyLocalActorCallDone() {
        runBlocking {
            verify(mockActorLocal, Mockito.times(1)).getActor(mockActorId)
        }
    }

    // LiveData

    protected fun verifyLiveDataEmitSuccess() {
        verifyLiveDataChanged(mockActorDetailSuccess)
    }

    protected fun verifyLiveDataEmitError() {
        verifyLiveDataChanged(mockActorDetailError)
    }

    private fun verifyLiveDataChanged(result: ActorDetailResult) {
        verify(mockObserver).onChanged(result)
    }

    protected fun observeLiveData() {
        subject.actorDetailResult.observeForever(mockObserver)
    }

    protected fun stopObserveLiveData() {
        subject.actorDetailResult.removeObserver(mockObserver)
    }

}