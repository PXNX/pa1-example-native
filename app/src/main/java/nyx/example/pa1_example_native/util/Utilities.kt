package nyx.example.pa1_example_native.util

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri

fun Context.openLink(url:String) = startActivity(
Intent(
Intent.ACTION_VIEW,
Uri.parse(url)
)
)

fun Int.isLandscape() = this == Configuration.ORIENTATION_LANDSCAPE
fun Int.isPortrait() = this == Configuration.ORIENTATION_PORTRAIT