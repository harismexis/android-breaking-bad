package com.harismexis.breakingbad.base

import com.harismexis.breakingbad.mocks.MockActorsProvider
import com.harismexis.breakingbad.reader.BaseFileReader

abstract class BaseTest {

    protected val mockActorsProvider by lazy { MockActorsProvider(getBaseFileReader()) }

    abstract fun getBaseFileReader(): BaseFileReader
}