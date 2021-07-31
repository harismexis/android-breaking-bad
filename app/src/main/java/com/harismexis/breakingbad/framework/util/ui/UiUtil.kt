package com.harismexis.breakingbad.framework.util.ui

import android.app.Activity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.snackbar.Snackbar
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.framework.extensions.getColorCompat

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.currentFocus?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.hideSystemUI() {
    WindowInsetsControllerCompat(this.window, this.window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Activity.showSystemUI() {
    WindowInsetsControllerCompat(
        this.window,
        this.window.decorView
    ).show(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
}

fun CoordinatorLayout.showSnackBar(
    msg: String
) {
    val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snack.show()
}

fun Activity.makeStatusBarDark() {
    makeStatusBarDark(R.color.default_background_color)
}

fun Activity.makeStatusBarDark(@ColorRes color: Int) {
    renderStatusBar(false, color)
}

fun Activity.renderStatusBar(isLight: Boolean, @ColorRes color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getColorCompat(color)
    val wic = WindowInsetsControllerCompat(window, window.decorView)
    wic.isAppearanceLightStatusBars = isLight
}