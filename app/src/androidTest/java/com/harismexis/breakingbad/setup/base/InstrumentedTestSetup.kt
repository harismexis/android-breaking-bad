package com.harismexis.breakingbad.setup.base

import com.harismexis.breakingbad.parser.MockActorsParser
import com.harismexis.breakingbad.setup.testutil.InstrumentedFileParser

open class InstrumentedTestSetup {

    protected val fileParser = InstrumentedFileParser()
    protected val actorsParser = MockActorsParser(fileParser)

}