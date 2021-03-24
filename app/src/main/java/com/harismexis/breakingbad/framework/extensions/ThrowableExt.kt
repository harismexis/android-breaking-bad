package com.harismexis.breakingbad.framework.extensions

fun Throwable.getErrorMessage(): String {
    var errorMsg = "$this, null error message"
    this.message?.let {
        errorMsg = it
    }
    return errorMsg
}
