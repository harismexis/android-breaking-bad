package com.example.jsonfeed.detail

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ItemDetailVmTest : ItemDetailVmTestSetup() {

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
        detailVm.retrieveItemById(mockId)

        // then
        verifyLocalCallDone()
        verifyLiveDataChanged()
    }

    @Test
    fun retrievingLocalItemThrowsError_nothingHappens() {
        // given
        mockLocalCallThrowsError()

        // when
        detailVm.retrieveItemById(mockId)

        // then
        verifyLocalCallDone()
        verifyLiveDataNotChanged()
    }

}