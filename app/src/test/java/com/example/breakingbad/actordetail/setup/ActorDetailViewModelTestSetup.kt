package com.example.breakingbad.actordetail.setup

import androidx.lifecycle.Observer
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.presentation.screens.actordetail.interactors.ActorDetailInteractors
import com.example.breakingbad.interactors.actor.IrrGetLocalActor
import com.example.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class ActorDetailViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIrrGetLocalItem: IrrGetLocalActor

    @Mock
    protected lateinit var mockInteractors: ActorDetailInteractors

    @Mock
    lateinit var mockObserver: Observer<Actor>

    lateinit var mockItem: Actor
    var mockId: Int = 0

    protected lateinit var subject: ActorDetailViewModel

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        mockItem = mockParser.getMockBBCharValid()
        mockId = mockItem.char_id
        subject = ActorDetailViewModel(mockInteractors)
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
            Mockito.`when`(mockIrrGetLocalItem.invoke(itemId)).thenReturn(item)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIrrGetLocalItem.invoke(any()))
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockIrrGetLocalItem, Mockito.times(1)).invoke(mockId)
        }
    }

    // LiveData

    protected fun verifyLiveDataChanged() {
        verifyLiveDataChanged(mockItem)
    }

    private fun verifyLiveDataChanged(item: Actor) {
        verify(mockObserver).onChanged(item)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    protected fun initialiseLiveData() {
        subject.model.observeForever(mockObserver)
    }

}