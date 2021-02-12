package com.example.jsonfeed.base

import com.example.jsonfeed.utils.InstrumentedMockParser

open class InstrumentedTestSetup: BaseTestSetup() {

    protected val mockParser = InstrumentedMockParser()

}