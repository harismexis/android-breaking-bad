package com.harismexis.breakingbad.home

import com.harismexis.breakingbad.home.setup.HomeViewModelTestSetup
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest : HomeViewModelTestSetup() {

    init {
        initialise()
    }

    @Before
    fun doBefore() {
        initialiseClassUnderTest()
        observeActorsLiveData()
    }

    @After
    fun doAfter() {
        stopObservingLiveData()
    }

    @Test
    fun internetOn_when_viewModelBinds_then_remoteActorsCallDone_actorsStored_liveDataUpdated() {
        // given
        mockRemoteActorsCallReturnsAllItemsValid()

        // when
        subject.fetchActors()

        // then
        verify_remoteActorsCallDone_actorsStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelBinds_then_localActorsFetched_liveDataUpdated() {
        // given
        mockLocalActorsCallReturnsAllItemsValid()

        // when
        subject.fetchActors()

        // then
        verifyRemoteActorsCallNotDone()
        verifyLocalActorsCallDone()
        verifyActorsNotStored()
        verifyActorsLiveDataChangedWithSuccess()
    }

    @Test
    fun remoteCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockRemoteActorsCallThrowsError()

        // when
        subject.fetchActors()

        // then
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallDone()
        verifyActorsLiveDataChangedWithError()
    }

    @Test
    fun localCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockLocalActorsCallThrowsError()

        // when
        subject.fetchActors()

        // then
        verifyRemoteActorsCallNotDone()
        verifyLocalActorsCallDone()
        verifyActorsLiveDataChangedWithError()
    }

    private fun verify_remoteActorsCallDone_actorsStored_liveDataUpdated() {
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallNotDone()
        verifyActorsLiveDataChangedWithSuccess()
        verifyActorsStored()
    }

}