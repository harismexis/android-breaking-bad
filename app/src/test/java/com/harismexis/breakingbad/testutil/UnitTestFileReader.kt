package com.harismexis.breakingbad.testutil

import com.harismexis.breakingbad.parser.BaseFileReader

class UnitTestFileReader : BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

