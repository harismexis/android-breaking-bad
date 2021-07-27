package com.harismexis.breakingbad.parser

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String

}