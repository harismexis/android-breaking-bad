package com.harismexis.breakingbad.framework.event


/**
 * Wrapper for data that is exposed via a LiveData that represents an event
 * eg. show toast on error
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}
