package com.example.breakingbad.bbcharacterdetail.setup

import androidx.lifecycle.Observer
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.presentation.interactors.BBActorDetailInteractors
import com.example.breakingbad.interactors.IrrGetLocalBBActor
import com.example.breakingbad.presentation.bbcharacterdetail.viewmodel.BBActorDetailViewModel
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class BBActorDetailViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIrrGetLocalItem: IrrGetLocalBBActor

    @Mock
    protected lateinit var mockInteractors: BBActorDetailInteractors

    @Mock
    lateinit var mockObserver: Observer<BBActor>

    lateinit var mockItem: BBActor
    var mockId: Int = 0

    protected lateinit var subject: BBActorDetailViewModel

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        mockItem = mockParser.getMockBBCharValid()
        mockId = mockItem.char_id
        subject = BBActorDetailViewModel(mockInteractors)
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
        item: BBActor
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

    private fun verifyLiveDataChanged(item: BBActor) {
        verify(mockObserver).onChanged(item)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    protected fun initialiseLiveData() {
        subject.model.observeForever(mockObserver)
    }

}