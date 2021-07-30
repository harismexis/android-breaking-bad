package com.harismexis.breakingbad.setup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harismexis.breakingbad.base.BaseTest
import com.harismexis.breakingbad.mocks.MockActorsProvider
import com.harismexis.breakingbad.util.UnitTestFileReader
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class BaseUnitTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val fileReader = UnitTestFileReader()
    protected val mockActorsProvider = MockActorsProvider(fileReader)

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
    }

    abstract fun initialiseClassUnderTest()
}

