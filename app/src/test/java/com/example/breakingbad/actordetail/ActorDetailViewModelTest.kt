package com.example.breakingbad.actordetail

import com.example.breakingbad.actordetail.setup.ActorDetailViewModelTestSetup
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ActorDetailViewModelTest : ActorDetailViewModelTestSetup() {

    init {
        initialise()
    }

    @Before
    fun doBeforeEachTestCase() {
        initialiseLiveData()
    }

    @Test
    fun retrievingLocalItem_localItemRetrievedAndLiveDataUpdated() {
        // given
        mockLocalCall()

        // when
        subject.retrieveItemById(mockId)

        // then
        verifyLocalCallDone()
        verifyLiveDataChanged()
    }

    @Test
    fun retrievingLocalItemThrowsError_nothingHappens() {
        // given
        mockLocalCallThrowsError()

        // when
        subject.retrieveItemById(mockId)

        // then
        verifyLocalCallDone()
        verifyLiveDataNotChanged()
    }

}