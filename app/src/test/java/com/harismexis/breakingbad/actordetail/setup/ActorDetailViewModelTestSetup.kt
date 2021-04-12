package com.harismexis.breakingbad.actordetail.setup

import androidx.lifecycle.Observer
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActor
import com.harismexis.breakingbad.presentation.result.ActorResult
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
    protected lateinit var mockIrrGetLocalItem: IrrGetLocalActor

    @Mock
    protected lateinit var mockInteractors: ActorDetailInteractors

    @Mock
    lateinit var mockObserver: Observer<ActorResult>

    lateinit var mockItem: Actor
    var mockId: Int = 0
    private lateinit var mockActorResultSuccess: ActorResult
    private lateinit var mockActorResultError: ActorResult
    protected lateinit var subject: ActorDetailViewModel

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        mockItem = actorsParser.getMockActorLocal()
        mockId = mockItem.char_id
        subject = ActorDetailViewModel(mockInteractors)
        mockActorResultSuccess = ActorResult.ActorSuccess(mockItem)
        mockActorResultError = ActorResult.ActorError(ERROR_MESSAGE)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.irrGetLocalItem).thenReturn(mockIrrGetLocalItem)
    }

    // Local Call

    protected fun mockLocalCall() {
        mockLocalCall(mockId, mockItem)
    }

    private fun mockLocalCall(
        itemId: Int,
        item: Actor
    ) {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalItem(itemId)).thenReturn(item)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalItem(any()))
                .thenThrow(IllegalStateException(ERROR_MESSAGE))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockIrrGetLocalItem, Mockito.times(1))(mockId)
        }
    }

    // LiveData

    protected fun verifyLiveDataChangedWithSuccess() {
        verifyLiveDataChanged(mockActorResultSuccess)
    }

    protected fun verifyLiveDataChangedWithError() {
        verifyLiveDataChanged(mockActorResultError)
    }

    private fun verifyLiveDataChanged(result: ActorResult) {
        verify(mockObserver).onChanged(result)
    }

    protected fun initialiseLiveData() {
        subject.model.observeForever(mockObserver)
    }

}