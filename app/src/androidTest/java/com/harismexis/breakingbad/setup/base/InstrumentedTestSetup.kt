package com.harismexis.breakingbad.setup.base

import com.harismexis.breakingbad.parser.ActorsMockParser
import com.harismexis.breakingbad.setup.testutil.InstrumentedFileParser

open class InstrumentedTestSetup {

    protected val fileParser = InstrumentedFileParser()
    protected val actorsParser = ActorsMockParser(fileParser)

}