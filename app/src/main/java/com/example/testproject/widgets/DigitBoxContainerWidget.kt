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

    private var hintEnabled = true

    fun initView(callback : (tag : Int)->Unit){
        binding.apply {
            (1..9).forEach{ it->
                getDigitBox(it)?.apply {
                    setData(it)
                    setOnClickListener { callback.invoke(this.getData()) }
                }
            }
        }
    }

    fun toggleHintEnabled(){
        hintEnabled = !hintEnabled
        (1..9).forEach {
            getDigitBox(it)?.toggleHintEnabled(hintEnabled)
        }
    }

    fun setSelectableState(selectableList : Set<Int>,isFixedWidget : Boolean){
        if(!hintEnabled){
            for(item in 1..9){
                val boxWidgetView = getDigitBox(item)
                if(isFixedWidget){
                    boxWidgetView?.setNotSelectable()
                }else{
                    boxWidgetView?.setSelectable()
                }
            }
            return
        }
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