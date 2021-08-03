package com.harismexis.breakingbad.tests.home

import com.harismexis.breakingbad.tests.home.setup.HomeViewModelTestSetup
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest : HomeViewModelTestSetup() {

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
    fun remoteActorsSuccess_actorsStored_liveDataEmitSuccess() {
        // given
        mockRemoteActorsCallSuccess()
        mockLocalActorsCallSuccess()

        // when
        subject.updateActors()

        // then
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallDone()
        verifyActorsStored()
        verifyLiveDataEmitSuccess()
    }

    @Test
    fun remoteActorsErrorAndCachedActorsSuccess_liveDataEmitSuccess() {
        // given
        mockRemoteActorsCallFailed()
        mockLocalActorsCallSuccess()

        // when
        subject.updateActors()

        // then
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallDone()
        verifyActorsNotStored()
        verifyLiveDataEmitSuccess()
    }

    @Test
    fun remoteAndLocalActorsError_liveDataEmitError() {
        // given
        mockRemoteActorsCallFailed()
        mockLocalActorsCallFailed()

        // when
        subject.updateActors()

        // then
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallDone()
        verifyLiveDataEmitError()
    }

}