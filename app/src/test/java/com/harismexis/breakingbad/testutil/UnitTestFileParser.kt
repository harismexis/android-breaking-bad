package com.harismexis.breakingbad.testutil

import com.harismexis.breakingbad.parser.BaseFileParser

class UnitTestFileParser : BaseFileParser() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

