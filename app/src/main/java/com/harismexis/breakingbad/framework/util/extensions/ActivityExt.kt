package com.harismexis.breakingbad.framework.util.extensions

import android.app.Activity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.harismexis.breakingbad.R

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.hideSystemUI() {
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Activity.showSystemUI() {
    WindowInsetsControllerCompat(window, window.decorView
    ).show(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
}

fun Activity.makeStatusBarDark() {
    makeStatusBarDark(R.color.default_bg_color)
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