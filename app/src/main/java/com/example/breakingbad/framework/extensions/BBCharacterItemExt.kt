package com.example.breakingbad.framework.extensions

import android.text.SpannableString
import android.text.Spanned
import androidx.core.text.HtmlCompat

fun linkSpanned(
    linkMissingMsg: String,
    linkMsg: String,
    url: String?
): Spanned {
    if (url.isNullOrBlank()) return SpannableString(linkMissingMsg)
    val linkedText = String.format("<a href=\"%s\">$linkMsg</a>", url)
    return HtmlCompat.fromHtml(
        linkedText,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}