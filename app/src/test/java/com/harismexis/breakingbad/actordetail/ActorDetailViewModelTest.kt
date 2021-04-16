package com.harismexis.breakingbad.actordetail

import com.harismexis.breakingbad.actordetail.setup.ActorDetailViewModelTestSetup
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
        initActorDetailLiveData()
    }

    @Test
    fun retrievingLocalActor_localActorRetrievedAndLiveDataUpdated() {
        // given
        mockLocalActorCall()

        // when
        subject.retrieveActorById(mockActorId)

        // then
        verifyLocalActorCallDone()
        verifyLiveDataChangedWithSuccess()
    }

    @Test
    fun retrievingLocalActorThrowsError_nothingHappens() {
        // given
        mockLocalActorCallThrowsError()

        // when
        subject.retrieveActorById(mockActorId)

        // then
        verifyLocalActorCallDone()
        verifyLiveDataChangedWithError()
    }

}