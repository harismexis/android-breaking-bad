package com.harismexis.breakingbad.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harismexis.breakingbad.reader.BaseFileReader
import com.harismexis.breakingbad.util.UnitTestFileReader
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class BaseUnitTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    open fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    override fun getBaseFileReader(): BaseFileReader {
        return UnitTestFileReader()
    }
}

