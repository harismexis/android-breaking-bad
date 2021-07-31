package com.harismexis.breakingbad.framework.util.extensions

import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

fun CoordinatorLayout.showSnackBar(
    msg: String
) {
    val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snack.show()
}
