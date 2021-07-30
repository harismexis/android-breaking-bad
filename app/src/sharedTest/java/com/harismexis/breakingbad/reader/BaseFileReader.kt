package com.harismexis.breakingbad.reader

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String

}