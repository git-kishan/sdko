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

    fun initView(callback : (tag : Int)->Unit){
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

            digitBox1.setOnClickListener {callback.invoke(digitBox1.getData())}
            digitBox2.setOnClickListener {callback.invoke(digitBox2.getData())}
            digitBox3.setOnClickListener {callback.invoke(digitBox3.getData())}
            digitBox4.setOnClickListener {callback.invoke(digitBox4.getData())}
            digitBox5.setOnClickListener {callback.invoke(digitBox5.getData())}
            digitBox6.setOnClickListener {callback.invoke(digitBox6.getData())}
            digitBox7.setOnClickListener {callback.invoke(digitBox7.getData())}
            digitBox8.setOnClickListener {callback.invoke(digitBox8.getData())}
            digitBox9.setOnClickListener {callback.invoke(digitBox9.getData())}

        }
    }


    fun setSelectableState(selectableList : Set<Int>,isFixedWidget : Boolean){
        if(isFixedWidget){
            for(item in 1..9){
                val boxWidgetView = getDigitBox(item)
                boxWidgetView?.setNotSelectable()
            }
            return
        }
        for(item in selectableList){
            val boxWidgetView = getDigitBox(item)
            boxWidgetView?.setSelectable()
        }
        for(item in 1..9){
            if(!selectableList.contains(item)){
                val boxWidgetView = getDigitBox(item)
                boxWidgetView?.setNotSelectable()
            }
        }
    }


    private fun getDigitBox(tag : Int) : DigitBoxWidget?{
        return when(tag){
            1 -> binding.digitBox1
            2 -> binding.digitBox2
            3 -> binding.digitBox3
            4 -> binding.digitBox4
            5 -> binding.digitBox5
            6 -> binding.digitBox6
            7 -> binding.digitBox7
            8 -> binding.digitBox8
            9 -> binding.digitBox9
            else -> null
        }
    }


}