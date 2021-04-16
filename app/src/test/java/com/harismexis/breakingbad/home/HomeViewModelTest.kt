package com.harismexis.breakingbad.home

import com.harismexis.breakingbad.home.setup.HomeViewModelTestSetup
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
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
    fun doBeforeEachTestCase() {
        initActorsLiveData()
    }

    @Test
    fun internetOn_when_viewModelBinds_then_remoteActorsCallDone_actorsStored_liveDataUpdated() {
        // given
        mockInternetActive(true)
        mockRemoteActorsCallReturnsAllItemsValid()

        // when
        subject.fetchInitialActors()

        // then
        verify_remoteActorsCallDone_actorsStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelBinds_then_localActorsFetched_liveDataUpdated() {
        // given
        mockInternetActive(false)
        mockLocalActorsCallReturnsAllItemsValid()

        // when
        subject.fetchInitialActors()

        // then
        verifyInternetChecked()
        verifyRemoteActorsCallNotDone()
        verifyLocalActorsCallDone()
        verifyActorsNotStored()
        verifyActorsLiveDataChangedWithSuccess()
    }

    @Test
    fun internetOn_when_viewModelRefreshes_then_remoteActorsRefreshed() {
        // given
        mockInternetActive(true)
        mockRemoteActorsCallReturnsAllItemsValid()

        // when
        subject.refresh {}

        // then
        verify_remoteActorsCallDone_actorsStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelRefreshes_then_nothingHappens() {
        // given
        mockInternetActive(false)

        // when
        subject.refresh {}

        // then
        verifyInternetChecked()
        verifyZeroInteractions(mockIrrGetRemoteActors)
        verifyZeroInteractions(mockIrrGetLocalActors)
        verifyActorsLiveDataNotChanged()
    }

    @Test
    fun remoteCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockInternetActive(true)
        mockRemoteActorsCallThrowsError()

        // when
        subject.fetchInitialActors()

        // then
        verifyInternetChecked()
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallNotDone()
        verifyActorsLiveDataChangedWithError()
    }

    @Test
    fun localCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockInternetActive(false)
        mockLocalActorsCallThrowsError()

        // when
        subject.fetchInitialActors()

        // then
        verifyInternetChecked()
        verifyRemoteActorsCallNotDone()
        verifyLocalActorsCallDone()
        verifyActorsLiveDataChangedWithError()
    }

    private fun verify_remoteActorsCallDone_actorsStored_liveDataUpdated() {
        verifyInternetChecked()
        verifyRemoteActorsCallDone()
        verifyLocalActorsCallNotDone()
        verifyActorsLiveDataChangedWithSuccess()
        verifyActorsStored()
    }

}