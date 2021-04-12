package com.harismexis.breakingbad.parser

abstract class BaseFileParser {

    abstract fun getFileAsString(filePath: String): String

}