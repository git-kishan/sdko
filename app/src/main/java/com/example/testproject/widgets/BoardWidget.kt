package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.algorithm.Generator
import com.example.testproject.databinding.BoardWidgetBinding
import com.example.testproject.utils.*

class BoardWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val boxViewMap = mutableMapOf<Int, BoxWidget>()
    private val generator = Generator(9, 35)
    private var startingBoard: Array<IntArray>? = null

    private val binding = DataBindingUtil.inflate<BoardWidgetBinding>(
        LayoutInflater.from(context),
        R.layout.board_widget,
        this, true
    )

    fun initView(dimension: Int, callback: (tag: Int) -> Unit) {
        val boardDimension = dimension
        val quadrantDimension = dimension / 3
        for (parentIndex in 1..9) {
            val quadrantWidget = QuadrantWidget(context)
            quadrantWidget.id = getBoxLayoutId(parentIndex)
            val params = boxLayoutFactory(parentIndex)
            binding.boardRootView.addView(quadrantWidget, params)
            quadrantWidget.initView(quadrantDimension, parentIndex, boxViewMap, callback)
        }
        generateBoard()
    }

    fun handleClick(tag: Int, callback: (optionList: Set<Int>, isFixed: Boolean) -> Unit) {
        clearPreviousSelection()
        colorHandling(tag)
        getSelectableOptions(tag, callback)
    }

    private fun clearPreviousSelection() {
        for (item in 1..81) {
            boxViewMap[item]?.clearClickedState()

        }
    }

    private fun colorHandling(tag: Int) {
        val rowItem = getRowTagList(tag)
        val columnItem = getColumnTagList(tag)
        rowItem.forEach { boxViewMap[it]?.handleShadowClickedState() }
        columnItem.forEach { boxViewMap[it]?.handleShadowClickedState() }
        boxViewMap[tag]?.handleClickedState()
    }

    private fun getSelectableOptions(
        tag: Int,
        callback: (optionList: Set<Int>, isFixed: Boolean) -> Unit
    ) {
        if (boxViewMap[tag]?.fixedData() == true) {
            callback.invoke(setOf(), true)
            return
        }
        val setOfDigit = mutableSetOf<Int>()
        (1..9).forEach { setOfDigit.add(it) }

        val rowItem = getRowTagList(tag)
        val columnItem = getColumnTagList(tag)
        val quadrantItem = getQuadrantTagList(tag)

        rowItem.forEach {
            val data = boxViewMap[it]?.getData()
            if (data.isNullOrEmpty()) return@forEach
            val digit = data.toInt()
            setOfDigit.remove(digit)
        }
        columnItem.forEach {
            val data = boxViewMap[it]?.getData()
            if (data.isNullOrEmpty()) return@forEach
            val digit = data.toInt()
            setOfDigit.remove(digit)
        }
        quadrantItem.forEach {
            val data = boxViewMap[it]?.getData()
            if (data.isNullOrEmpty()) return@forEach
            val digit = data.toInt()
            setOfDigit.remove(digit)
        }
        callback.invoke(setOfDigit, false)
    }

    private fun generateBoard() {
        startingBoard = generator.generateBoard()
        startingBoard?.let {
            for (i in 0 until 9) {
                for (j in 0 until 9) {
                    val tagNo = i * 9 + j + 1
                    if (it[i][j] != 0)
                        boxViewMap[tagNo]?.setFixedData(it[i][j].toString())
                }
            }
        }
    }
}