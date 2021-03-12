package com.example.breakingbad.framework.extensions

import android.text.SpannableString
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.breakingbad.domain.BBCharacter

fun BBCharacter?.wikipediaSpanned(
    wikiLinkMissingMsg: String,
    wikiLinkMsg: String
): Spanned {
    if (this == null || this.wikipediaUrl.isNullOrBlank())
        return SpannableString(wikiLinkMissingMsg)
    val linkedText = String.format("<a href=\"%s\">$wikiLinkMsg</a>", this.wikipediaUrl)
    return HtmlCompat.fromHtml(
        linkedText,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}