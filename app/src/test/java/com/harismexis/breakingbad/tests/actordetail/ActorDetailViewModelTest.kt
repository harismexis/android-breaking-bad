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
    fun getCachedActorSuccess_liveDataEmitSuccess() {
        // given
        mockLocalActorCallSuccess()

        // when
        subject.retrieveActorById(mockActorId)

        // then
        verifyLocalActorCallDone()
        verifyLiveDataEmitSuccess()
    }

    @Test
    fun getCachedActorFailed_liveDataEmitError() {
        // given
        mockLocalActorCallFailed()

        // when
        subject.retrieveActorById(mockActorId)

        // then
        verifyLocalActorCallDone()
        verifyLiveDataEmitError()
    }

}