package com.harismexis.breakingbad.setup.base

import com.harismexis.breakingbad.parser.MockActorsProvider
import com.harismexis.breakingbad.setup.testutil.InstrumentedFileReader

open class InstrumentedTestSetup {

    protected val fileParser = InstrumentedFileReader()
    protected val actorsParser = MockActorsProvider(fileParser)

}