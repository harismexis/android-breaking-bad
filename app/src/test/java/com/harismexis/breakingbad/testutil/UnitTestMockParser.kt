package com.harismexis.breakingbad.testutil

import com.harismexis.breakingbad.parser.BaseMockParser

class UnitTestMockParser : BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

