package com.harismexis.breakingbad.setup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harismexis.breakingbad.base.BaseTestSetup
import com.harismexis.breakingbad.parser.MockActorsProvider
import com.harismexis.breakingbad.testutil.UnitTestFileReader
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val fileReader = UnitTestFileReader()
    protected val mockActorsProvider = MockActorsProvider(fileReader)

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
    }

    abstract fun initialiseClassUnderTest()
}

