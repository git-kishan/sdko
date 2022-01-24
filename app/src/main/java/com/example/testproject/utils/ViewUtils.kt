package com.example.testproject.utils

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import com.example.testproject.R

fun setForcedLightTheme(resources : Resources) {
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES, Configuration.UI_MODE_NIGHT_NO ->
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

fun getBoxLayoutId(position: Int): Int {
    return when (position) {
        1 -> R.id.layout_box_1
        2 -> R.id.layout_box_2
        3 -> R.id.layout_box_3
        4 -> R.id.layout_box_4
        5 -> R.id.layout_box_5
        6 -> R.id.layout_box_6
        7 -> R.id.layout_box_7
        8 -> R.id.layout_box_8
        9 -> R.id.layout_box_9
        else -> -1
    }
}

fun getBoxId(position: Int): Int {
    return when (position) {
        1 -> R.id.box_1
        2 -> R.id.box_2
        3 -> R.id.box_3
        4 -> R.id.box_4
        5 -> R.id.box_5
        6 -> R.id.box_6
        7 -> R.id.box_7
        8 -> R.id.box_8
        9 -> R.id.box_9
        else -> -1
    }
}