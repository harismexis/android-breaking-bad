package com.harismexis.breakingbad.base

import com.harismexis.breakingbad.mocks.MockActorsProvider
import com.harismexis.breakingbad.util.InstrumentedFileReader

open class BaseInstrumentedTest {

    protected val mockActorsProvider = MockActorsProvider(InstrumentedFileReader())
}