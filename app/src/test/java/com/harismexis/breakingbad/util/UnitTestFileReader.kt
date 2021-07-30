package com.harismexis.breakingbad.util

import com.harismexis.breakingbad.reader.BaseFileReader

class UnitTestFileReader : BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

