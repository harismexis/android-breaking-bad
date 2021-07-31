package com.harismexis.breakingbad.setup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harismexis.breakingbad.base.BaseTest
import com.harismexis.breakingbad.reader.BaseFileReader
import com.harismexis.breakingbad.util.UnitTestFileReader
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class BaseUnitTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    abstract fun initialiseClassUnderTest()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
    }

    override fun getBaseFileReader(): BaseFileReader {
        return UnitTestFileReader()
    }
}

