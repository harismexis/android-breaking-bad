package com.example.jsonfeed.instrumentedsetup.base

import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.instrumentedsetup.instrumentedutil.InstrumentedMockParser

open class InstrumentedTestSetup: BaseTestSetup() {

    protected val mockParser = InstrumentedMockParser()

}