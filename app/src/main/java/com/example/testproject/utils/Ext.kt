package com.example.testproject.utils

import android.content.Context
import com.example.testproject.R

val leftBoundaryItem = listOf(1, 4, 7)
val topBoundaryItem = listOf(1, 2, 3)
val rightBoundaryItem = listOf(3, 6, 9)
val bottomBoundaryItem = listOf(7, 8, 9)

fun getScreenHeightInPx(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

fun getScreenWidthInPx(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun dpToPx(context: Context, dp: Int) : Int {
    return (context.resources.displayMetrics.density * dp +0.5).toInt()
}

fun getBoxTag(parentIndex : Int, childIndex : Int) : Int{
    val boxAbove = (((parentIndex-1)/3)*3 + ((childIndex-1)/3))*9
    val boxBefore = ((parentIndex-1)%3)*3 + (childIndex-1)%3 +1
    val tag = boxAbove + boxBefore
    return tag
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