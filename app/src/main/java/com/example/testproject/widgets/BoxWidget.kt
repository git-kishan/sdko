package com.example.testproject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.databinding.BoxWidgetBinding

class BoxWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textSizePercentage = 0.18f
    private val binding = DataBindingUtil.inflate<BoxWidgetBinding>(
        LayoutInflater.from(context),
        R.layout.box_widget,
        this, true
    )
    private var fixedData = false

    fun initView(dimension: Int, i: Int) {
        val boxDimension = dimension
        setRootDimension(dimension)
        setTextSize(dimension * textSizePercentage)
    }

    fun isFixedData() : Boolean{
        return fixedData
    }

    fun attachListeners(callback : (tag : Int)->Unit){
        binding.boxRootView.setOnClickListener {
            callback.invoke(tag as Int)
        }
    }

    fun setFixedData(value : String){
        setData(value)
        fixedData = true
        binding.boxView.setTextColor(ResourcesCompat.getColor(resources,R.color.black,null))
    }

    fun setData(value: String) {
        binding.boxView.text = value
    }
    fun getData(): String{
        return binding.boxView.text.trim().toString()
    }

    private fun setTextSize(sizeInPx: Float) {
        binding.boxView.textSize = sizeInPx
    }

    fun handleClickedState(){
        binding.boxView.apply {
            setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))
            setBackgroundColor(ResourcesCompat.getColor(resources,R.color.selected_box,null))
        }
    }

    fun handleShadowClickedState(){
        binding.boxView.apply {
            setBackgroundColor(ResourcesCompat.getColor(resources,R.color.shadow_selected_box,null))
        }
    }

    fun clearClickedState(){
        binding.boxView.apply {
            setTextColor(ResourcesCompat.getColor(resources,R.color.box_text_color,null))
            setBackgroundColor(ResourcesCompat.getColor(resources,android.R.color.transparent,null))
            if(fixedData){
              setTextColor(ResourcesCompat.getColor(resources,R.color.black,null))
            }
        }
    }

    private fun setRootDimension(dimension: Int) {
        val layoutParams = binding.boxRootView.layoutParams
        layoutParams.width = dimension
        layoutParams.height = dimension
        binding.boxRootView.layoutParams = layoutParams
    }


}