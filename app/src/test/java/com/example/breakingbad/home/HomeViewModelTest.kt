package com.example.breakingbad.home

import com.example.breakingbad.home.setup.HomeViewModelTestSetup
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
        initialiseLiveData()
    }

    @Test
    fun internetOn_when_viewModelBinds_then_remoteCallDone_dataStored_liveDataUpdated() {
        // given
        mockInternetOn()
        mockRemoteCallReturnsAllItemsValid()

        // when
        subject.bind()

        // then
        verify_remoteFeedCallDone_dataStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelBinds_then_localItemsFetched_liveDataUpdated() {
        // given
        mockInternetOff()
        mockLocalCallReturnsAllItemsValid()

        // when
        subject.bind()

        // then
        verifyInternetChecked()
        verifyRemoteCallNotDone()
        verifyLocalCallDone()
        verifyDataNotStored()
        verifyLiveDataChangedAsExpected()
    }

    // TODO: Update Test, probably will use only get actors by name
    // @Test
    fun internetOn_when_viewModelRefreshes_then_dataRefreshed() {
        // given
        mockInternetOn()
        mockRemoteCallReturnsAllItemsValid()

        // when
        subject.refresh {}

        // then
        verify_remoteFeedCallDone_dataStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelRefreshes_then_nothingHappens() {
        // given
        mockInternetOff()

        // when
        subject.refresh {}

        // then
        verifyInternetChecked()
        verifyZeroInteractions(mockIrrGetRemoteItems)
        verifyZeroInteractions(mockIrrGetLocalItems)
        verifyLiveDataNotChanged()
    }

    @Test
    fun remoteCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockInternetOn()
        mockRemoteCallThrowsError()

        // when
        subject.bind()

        // then
        verifyInternetChecked()
        verifyRemoteCallDone()
        verifyLocalCallNotDone()
        verifyLiveDataNotChanged()
    }

    @Test
    fun localCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockInternetOff()
        mockLocalCallThrowsError()

        // when
        subject.bind()

        // then
        verifyInternetChecked()
        verifyRemoteCallNotDone()
        verifyLocalCallDone()
        verifyLiveDataNotChanged()
    }

    private fun verify_remoteFeedCallDone_dataStored_liveDataUpdated() {
        verifyInternetChecked()
        verifyRemoteCallDone()
        verifyLocalCallNotDone()
        verifyLiveDataChangedAsExpected()
        verifyDataStored()
    }

}