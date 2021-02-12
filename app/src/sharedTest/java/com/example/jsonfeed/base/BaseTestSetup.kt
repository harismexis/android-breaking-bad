package com.example.jsonfeed.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jsonfeed.rules.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseTestSetup {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

}