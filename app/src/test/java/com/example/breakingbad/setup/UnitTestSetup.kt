package com.example.breakingbad.setup

import com.example.breakingbad.base.BaseTestSetup
import com.example.breakingbad.testutil.UnitTestMockParser
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val mockParser = UnitTestMockParser()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}