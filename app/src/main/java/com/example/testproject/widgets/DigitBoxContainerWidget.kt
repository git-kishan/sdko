package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.databinding.DigitBoxContainerWidgetBinding

class DigitBoxContainerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = DataBindingUtil.inflate<DigitBoxContainerWidgetBinding>(
        LayoutInflater.from(context),
        R.layout.digit_box_container_widget,
        this,true
    )

    fun initView(callback : (digitBoxWidget : DigitBoxWidget)->Unit){
        binding.apply {
            digitBox1.setData(1)
            digitBox2.setData(2)
            digitBox3.setData(3)
            digitBox4.setData(4)
            digitBox5.setData(5)
            digitBox6.setData(6)
            digitBox7.setData(7)
            digitBox8.setData(8)
            digitBox9.setData(9)

            digitBox1.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox2.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox3.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox4.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox5.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox6.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox7.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox8.setOnClickListener {callback.invoke(it as DigitBoxWidget)}
            digitBox9.setOnClickListener {callback.invoke(it as DigitBoxWidget)}

        }

    }


}