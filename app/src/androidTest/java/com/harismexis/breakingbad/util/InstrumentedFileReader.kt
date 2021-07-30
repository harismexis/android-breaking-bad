package com.harismexis.breakingbad.util

import androidx.test.platform.app.InstrumentationRegistry
import com.harismexis.breakingbad.reader.BaseFileReader

class InstrumentedFileReader: BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

