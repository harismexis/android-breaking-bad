package com.harismexis.breakingbad.framework.util.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.framework.util.jsonToObject

fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.getGlideErrorColor(): Int {
    return this.getColorCompat(R.color.grey_four)
}

fun Context.getGlideErrorColorDrawable(): ColorDrawable {
    return ColorDrawable(this.getGlideErrorColor())
}

fun Context.populateWithGlide(
    imageView: ImageView,
    url: String?
) {
    if (url.isNullOrBlank()) {
        imageView.setImageDrawable(this.getGlideErrorColorDrawable())
        return
    }
    Glide.with(this)
        .load(Uri.parse(url))
        .error(this.getGlideErrorColorDrawable())
        .placeholder(R.drawable.loading_animation)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imageView)
}

fun Context.showToast(msg: String) {
    val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun Context.getRawTextFile(@RawRes id: Int) =
    this.resources.openRawResource(id).bufferedReader().use { it.readText() }

fun Context.getVideosFromRaw() =
    jsonToObject<List<Video>>(this.applicationContext.getRawTextFile(R.raw.youtube_videos))

