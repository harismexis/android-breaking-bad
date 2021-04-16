package com.harismexis.breakingbad.actordetail.setup

import androidx.lifecycle.Observer
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActor
import com.harismexis.breakingbad.presentation.result.ActorDetailResult
import com.harismexis.breakingbad.presentation.screens.actordetail.interactors.ActorDetailInteractors
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class ActorDetailViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIrrGetLocalActor: IrrGetLocalActor
    @Mock
    protected lateinit var mockActorDetailInteractors: ActorDetailInteractors
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

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        mockActor = actorsParser.getMockLocalActor()
        mockActorId = mockActor.char_id
        subject = ActorDetailViewModel(mockActorDetailInteractors)
        mockActorDetailSuccess = ActorDetailResult.ActorSuccess(mockActor)
        mockActorDetailError = ActorDetailResult.ActorError(ERROR_MESSAGE)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockActorDetailInteractors.irrGetLocalItem).thenReturn(mockIrrGetLocalActor)
    }

    // Local Call

    protected fun mockLocalActorCall() {
        mockLocalActorCall(mockActorId, mockActor)
    }

    private fun mockLocalActorCall(
        actorId: Int,
        actor: Actor
    ) {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalActor(actorId)).thenReturn(actor)
        }
    }

    protected fun mockLocalActorCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalActor(any()))
                .thenThrow(IllegalStateException(ERROR_MESSAGE))
        }
    }

    protected fun verifyLocalActorCallDone() {
        runBlocking {
            verify(mockIrrGetLocalActor, Mockito.times(1))(mockActorId)
        }
    }

    // LiveData

    protected fun verifyLiveDataChangedWithSuccess() {
        verifyLiveDataChanged(mockActorDetailSuccess)
    }

    protected fun verifyLiveDataChangedWithError() {
        verifyLiveDataChanged(mockActorDetailError)
    }

    private fun verifyLiveDataChanged(result: ActorDetailResult) {
        verify(mockObserver).onChanged(result)
    }

    protected fun initActorDetailLiveData() {
        subject.actorDetailResult.observeForever(mockObserver)
    }

}