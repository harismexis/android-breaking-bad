package com.harismexis.breakingbad.setup

import com.harismexis.breakingbad.base.BaseTestSetup
import com.harismexis.breakingbad.parser.ActorsMockParser
import com.harismexis.breakingbad.testutil.UnitTestFileParser
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val fileParser = UnitTestFileParser()
    protected val actorsParser = ActorsMockParser(fileParser)

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}