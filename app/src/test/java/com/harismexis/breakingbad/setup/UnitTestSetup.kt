package com.harismexis.breakingbad.setup

import com.harismexis.breakingbad.base.BaseTestSetup
import com.harismexis.breakingbad.parser.MockActorsProvider
import com.harismexis.breakingbad.testutil.UnitTestFileReader
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val fileReader = UnitTestFileReader()
    protected val mockActorsProvider = MockActorsProvider(fileReader)

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
    }

    abstract fun initialiseClassUnderTest()
}

