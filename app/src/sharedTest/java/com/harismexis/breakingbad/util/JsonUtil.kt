package com.harismexis.breakingbad.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

inline fun <reified T> toObject(jsonString: String?): T {
    val gson = Gson()
    val json = gson.fromJson(jsonString, JsonObject::class.java)
    return gson.fromJson(json, T::class.java)
}

inline fun <reified T> jsonToObject(json: String?): T =
    Gson().fromJson(json, object : TypeToken<T>() {}.type)