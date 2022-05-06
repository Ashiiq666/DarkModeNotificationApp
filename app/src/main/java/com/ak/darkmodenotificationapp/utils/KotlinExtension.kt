package com.ak.darkmodenotificationapp.utils
import android.content.Context
import android.content.res.Configuration
import android.view.View

fun Context.checkNightMode(): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> {
            true
        }
        Configuration.UI_MODE_NIGHT_NO -> {
            false
        }
        Configuration.UI_MODE_NIGHT_UNDEFINED -> {
            false
        }
        else -> {
            false
        }
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

