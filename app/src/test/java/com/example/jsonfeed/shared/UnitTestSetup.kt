package com.example.jsonfeed.shared

import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.util.UnitTestMockParser
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val mockParser = UnitTestMockParser()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}