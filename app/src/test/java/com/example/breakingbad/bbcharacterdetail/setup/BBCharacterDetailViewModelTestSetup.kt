package com.example.breakingbad.bbcharacterdetail.setup

import androidx.lifecycle.Observer
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.interactors.BBCharacterDetailInteractors
import com.example.breakingbad.interactors.InterGetLocalBBCharacter
import com.example.breakingbad.presentation.bbcharacterdetail.viewmodel.BBCharacterDetailViewModel
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class BBCharacterDetailViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockInterGetLocalItem: InterGetLocalBBCharacter

    @Mock
    protected lateinit var mockInteractors: BBCharacterDetailInteractors

    @Mock
    lateinit var mockObserver: Observer<BBCharacter>

    lateinit var mockItem: BBCharacter
    var mockId: Int = 0

    protected lateinit var subject: BBCharacterDetailViewModel

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        mockItem = mockParser.getMockBBCharValid()
        mockId = mockItem.char_id
        subject = BBCharacterDetailViewModel(mockInteractors)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.interGetLocalItem).thenReturn(mockInterGetLocalItem)
    }

    // Local Call

    protected fun mockLocalCall() {
        mockLocalCall(mockId, mockItem)
    }

    private fun mockLocalCall(
        itemId: Int,
        item: BBCharacter
    ) {
        runBlocking {
            Mockito.`when`(mockInterGetLocalItem.invoke(itemId)).thenReturn(item)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInterGetLocalItem.invoke(any()))
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockInterGetLocalItem, Mockito.times(1)).invoke(mockId)
        }
    }

    // LiveData

    protected fun verifyLiveDataChanged() {
        verifyLiveDataChanged(mockItem)
    }

    private fun verifyLiveDataChanged(item: BBCharacter) {
        verify(mockObserver).onChanged(item)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    protected fun initialiseLiveData() {
        subject.model.observeForever(mockObserver)
    }

}