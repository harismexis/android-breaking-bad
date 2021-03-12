package com.example.breakingbad.setup.testutil

import androidx.test.platform.app.InstrumentationRegistry

import com.example.breakingbad.parser.BaseMockParser

class InstrumentedMockParser: BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

