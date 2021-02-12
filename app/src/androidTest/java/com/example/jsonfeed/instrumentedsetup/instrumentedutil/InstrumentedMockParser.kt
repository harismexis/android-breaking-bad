package com.example.jsonfeed.instrumentedsetup.instrumentedutil

import androidx.test.platform.app.InstrumentationRegistry

import com.example.jsonfeed.parser.BaseMockParser

class InstrumentedMockParser: BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

