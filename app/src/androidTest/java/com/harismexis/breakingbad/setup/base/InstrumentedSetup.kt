package com.harismexis.breakingbad.setup.base

import com.harismexis.breakingbad.parser.MockActorsProvider
import com.harismexis.breakingbad.setup.testutil.InstrumentedFileReader

open class InstrumentedSetup {

    protected val fileReader = InstrumentedFileReader()
    protected val mockActorsProvider = MockActorsProvider(fileReader)

}