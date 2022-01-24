package com.example.testproject.utils

import android.widget.RelativeLayout

fun boxLayoutFactory(position : Int) : RelativeLayout.LayoutParams{
    val params = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    setConstraints(params, position,true)
    return params
}

fun boxFactory(position : Int) : RelativeLayout.LayoutParams{
    val params = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    setConstraints(params, position,false)
    return params
}



private fun setConstraints(params : RelativeLayout.LayoutParams , position: Int ,layout : Boolean){
    when(position){
        1 -> {
            params.addRule(RelativeLayout.ALIGN_PARENT_START , RelativeLayout.TRUE)
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP , RelativeLayout.TRUE)
        }
        2 ->{
            params.addRule(RelativeLayout.END_OF , getId(1,layout))
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE)
        }
        3-> {
            params.addRule(RelativeLayout.END_OF , getId(2,layout))
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE)
        }
        4 -> {
            params.addRule(RelativeLayout.ALIGN_PARENT_START , RelativeLayout.TRUE)
            params.addRule(RelativeLayout.BELOW, getId(1,layout))
        }
        5 -> {
            params.addRule(RelativeLayout.END_OF , getId(4,layout))
            params.addRule(RelativeLayout.BELOW , getId(2,layout))
        }
        6 -> {
            params.addRule(RelativeLayout.END_OF , getId(5,layout))
            params.addRule(RelativeLayout.BELOW , getId(3,layout))
        }
        7 -> {
            params.addRule(RelativeLayout.ALIGN_PARENT_START , RelativeLayout.TRUE)
            params.addRule(RelativeLayout.BELOW, getId(4,layout))
        }
        8 -> {
            params.addRule(RelativeLayout.END_OF, getId(7,layout))
            params.addRule(RelativeLayout.BELOW, getId(5, layout))
        }
        9 -> {
            params.addRule(RelativeLayout.END_OF, getId(8,layout))
            params.addRule(RelativeLayout.BELOW, getId(6,layout))
        }
    }
}

private fun getId(position: Int , layout: Boolean ): Int {
    if(layout){
        return getBoxLayoutId(position)
    }
    return getBoxId(position)
}