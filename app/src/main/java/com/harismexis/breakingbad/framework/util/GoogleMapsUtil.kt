package com.harismexis.breakingbad.framework.util

import android.content.Intent
import android.net.Uri

fun googleMapsNewMexicoIntent(): Intent {
    // val gmmIntentUri = Uri.parse("geo:35.0826103f,-106.8165672f?z=10")
    val gmmIntentUri = Uri.parse("geo:0,0?q=Albuquerque?z=10")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    return mapIntent
}
