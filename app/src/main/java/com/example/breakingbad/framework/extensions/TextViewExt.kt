package com.example.breakingbad.framework.extensions

import android.widget.TextView
import com.example.breakingbad.R

fun TextView?.setTextOrDefault(value: String?, defaultValue: String) {
    if (this == null) return
    if (value.isNullOrBlank()) {
        this.text = defaultValue
        return
    }
    this.text = value
}

fun TextView?.setTextOrUnknown(value: String?) {
    if (this == null) return
    this.setTextOrDefault(value, this.context.getString(R.string.missing_value))
}
