package com.harismexis.breakingbad.base

import com.harismexis.breakingbad.reader.BaseFileReader
import com.harismexis.breakingbad.util.InstrumentedFileReader

open class BaseInstrumentedTest: BaseTest() {

    override fun getBaseFileReader(): BaseFileReader {
        return InstrumentedFileReader()
    }
}