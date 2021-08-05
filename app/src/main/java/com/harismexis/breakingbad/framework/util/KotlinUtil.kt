package com.harismexis.breakingbad.framework.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <T : Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}

inline fun <reified T> jsonToObject(json: String?): T =
    Gson().fromJson(json, object : TypeToken<T>() {}.type)