package com.harismexis.breakingbad.util

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import com.harismexis.breakingbad.R

fun getStringRes(@StringRes id: Int): String {
    return InstrumentationRegistry.getInstrumentation()
        .targetContext.resources.getString(id)
}

fun getExpectedText(value: String?): String {
    return if (value.isNullOrBlank()) getStringRes(R.string.missing_value)
    else value
}