package com.harismexis.breakingbad.framework.util.extensions

import android.app.Dialog
import android.view.ViewGroup

fun Dialog?.makeFullScreen() {
    this?.let {
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        it.window?.setLayout(width, height)
    }
}
