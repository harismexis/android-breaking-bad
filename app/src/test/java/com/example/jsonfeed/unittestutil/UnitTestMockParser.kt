package com.example.jsonfeed.unittestutil

import com.example.jsonfeed.parser.BaseMockParser

class UnitTestMockParser : BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

