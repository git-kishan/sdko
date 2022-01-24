package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.databinding.QuadrantWidgetBinding
import com.example.testproject.utils.boxFactory
import com.example.testproject.utils.getBoxId
import com.example.testproject.utils.getBoxTag


class QuadrantWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding = DataBindingUtil.inflate<QuadrantWidgetBinding>(
        LayoutInflater.from(context),
        R.layout.quadrant_widget,
        this, true
    )

    fun initView(dimension: Int, parentIndex: Int, map: MutableMap<Int, BoxWidget>,callback : (tag : Int)->Unit) {
        val quadrantDimension = dimension
        val boxDimension = dimension / 3
        for (childIndex in 1..9) {
            val boxWidget = BoxWidget(context)
            boxWidget.id = getBoxId(childIndex)
            val params = boxFactory(childIndex)
            val tag = getBoxTag(parentIndex, childIndex)
            boxWidget.tag = tag
            binding.quadrantRootView.addView(boxWidget, params)
            boxWidget.initView(boxDimension, childIndex)
            boxWidget.attachListeners(callback)
            map[tag] = boxWidget
        }
    }
}