package com.example.jsonfeed.utils

import androidx.test.platform.app.InstrumentationRegistry

import com.example.jsonfeed.parser.BaseMockParser

class InstrumentedMockParser: BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

