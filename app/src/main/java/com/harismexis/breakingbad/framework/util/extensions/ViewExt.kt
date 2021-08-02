package com.harismexis.breakingbad.framework.util.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    msg: String
) {
    val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snack.show()
}
