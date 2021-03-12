package com.example.breakingbad.testutil

import com.example.breakingbad.parser.BaseMockParser

class UnitTestMockParser : BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

