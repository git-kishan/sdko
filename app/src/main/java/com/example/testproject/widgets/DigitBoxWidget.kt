package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.databinding.DigitBoxWidgetBinding

class DigitBoxWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding = DataBindingUtil.inflate<DigitBoxWidgetBinding>(
        LayoutInflater.from(context),
        R.layout.digit_box_widget,
        this, true
    )

    fun setData(digit: Int) {
        binding.digitBox.text = digit.toString()
    }

    fun getData(): Int {
        return binding.digitBox.text.toString().toInt()
    }

    fun setSelectable() {
        binding.digitBoxRootView.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.digit_box_selectable_state,
            null
        )
        binding.digitBox.setTextColor(ResourcesCompat.getColor(
            resources, R.color.black, null
        ))
    }

    fun setNotSelectable() {
        binding.digitBoxRootView.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.digit_box_non_selectable_state,
            null
        )
        binding.digitBox.setTextColor(ResourcesCompat.getColor(
            resources, R.color.white, null
        ))
    }
    fun setNormalState(){
        binding.digitBox.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.digit_box_background,
            null
        )
    }

}