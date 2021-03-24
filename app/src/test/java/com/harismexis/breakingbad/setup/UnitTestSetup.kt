package com.harismexis.breakingbad.setup

import com.harismexis.breakingbad.base.BaseTestSetup
import com.harismexis.breakingbad.testutil.UnitTestMockParser
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val mockParser = UnitTestMockParser()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}