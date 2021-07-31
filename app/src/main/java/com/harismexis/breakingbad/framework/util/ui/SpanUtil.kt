package com.harismexis.breakingbad.framework.util.ui

import android.text.SpannableString
import android.text.Spanned
import androidx.core.text.HtmlCompat

fun getLinkSpanned(
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