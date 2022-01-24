package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
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

    fun setData(digit : Int){
        binding.digitBox.text  = digit.toString()
    }

    fun getData() : String{
        return binding.digitBox.text.toString()
    }

}