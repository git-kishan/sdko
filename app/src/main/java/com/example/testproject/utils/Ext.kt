package com.example.testproject.utils

import android.content.Context
import android.util.Log
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

fun getRowTagList(tag : Int) : List<Int>{
    val list = mutableListOf<Int>()
    val firstItemInRow = ((tag - 1) / 9) * 9 + 1
    val lastItem = firstItemInRow + 8
    (firstItemInRow..lastItem).forEach { list.add(it) }
    return list
}

fun getColumnTagList(tag : Int) : List<Int>{
    val list = mutableListOf<Int>()
    list.add(tag)
    var currIndex = tag - 9
    while (currIndex > 0) {
        list.add(currIndex)
        currIndex -= 9
    }
    currIndex = tag + 9
    while (currIndex <= 81) {
        list.add(currIndex)
        currIndex += 9
    }
    return list
}

fun getQuadrantTagList(tag : Int) : List<Int>{
    val list = mutableListOf<Int>()
    val boxColIndex = ((tag - 1) % 9 ) / 3 + 1
    val boxRowIndex = (tag - 1) / 27 + 1
    val startTag = (boxRowIndex-1)*9*3+(boxColIndex-1)*3 + 1
    Log.d("klogs","$boxRowIndex , $boxColIndex , $startTag")
    (startTag..(startTag+2)).forEach {
        list.add(it)
        list.add(it+9)
        list.add(it+18)
    }
    return list
}