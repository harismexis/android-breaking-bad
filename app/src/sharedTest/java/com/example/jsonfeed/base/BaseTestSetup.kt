package com.example.jsonfeed.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jsonfeed.rules.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseTestSetup {

    // Needed for the Unit Tests, to move to there probably
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    @ExperimentalCoroutinesApi
//    @get:Rule
//    val coroutineScope = MainCoroutineScopeRule()

}