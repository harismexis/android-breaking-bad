package com.example.breakingbad.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainApplicationTest {

    @Test
    fun appContext_isNotNull() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        assertNotNull(appContext)
    }
}
