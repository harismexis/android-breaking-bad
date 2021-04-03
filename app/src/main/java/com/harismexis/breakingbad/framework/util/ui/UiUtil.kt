package com.harismexis.breakingbad.framework.util.ui

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.currentFocus?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }

}