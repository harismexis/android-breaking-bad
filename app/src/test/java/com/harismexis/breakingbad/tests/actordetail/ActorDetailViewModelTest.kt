package com.harismexis.breakingbad.tests.actordetail

import com.harismexis.breakingbad.tests.actordetail.setup.ActorDetailViewModelTestSetup
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ActorDetailViewModelTest : ActorDetailViewModelTestSetup() {

    @Before
    fun doBefore() {
        initMocks()
        initSubject()
        observeLiveData()
    }

    @After
    fun doAfter() {
        stopObserveLiveData()
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