package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.algorithm.Generator
import com.example.testproject.databinding.BoardWidgetBinding
import com.example.testproject.utils.boxLayoutFactory
import com.example.testproject.utils.getBoxLayoutId

class BoardWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val boxViewMap = mutableMapOf<Int, BoxWidget>()
    private val generator = Generator(9, 20)
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

    fun handleClick(tag: Int) {
        clearPreviousSelection()
        colorHandling(tag)
    }

    private fun clearPreviousSelection() {
        for (item in 1..81) {
               boxViewMap[item]?.clearClickedState()

        }
    }

    private fun colorHandling(tag: Int) {
        val firstItemInRow = ((tag - 1) / 9) * 9 + 1
        for (index in firstItemInRow until firstItemInRow + 9) {
            boxViewMap[index]?.handleShadowClickedState()
        }
        var currIndex = tag - 9
        while (currIndex > 0) {
            boxViewMap[currIndex]?.handleShadowClickedState()
            currIndex -= 9
        }
        currIndex = tag + 9
        while (currIndex <= 81) {
            boxViewMap[currIndex]?.handleShadowClickedState()
            currIndex += 9
        }
        boxViewMap[tag]?.handleClickedState()
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