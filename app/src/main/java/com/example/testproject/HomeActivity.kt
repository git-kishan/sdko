package com.example.testproject

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.testproject.databinding.ActivityHomeBinding
import com.example.testproject.utils.dpToPx
import com.example.testproject.utils.getScreenWidthInPx
import com.example.testproject.utils.setForcedLightTheme
import com.example.testproject.widgets.DigitBoxWidget

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setForcedLightTheme(resources)
        init()
    }

    private fun init() {
        binding.apply {
            digitBoxContainerWidget.initView(::digitBoxClickHandler)
            boardWidget.initView(
                getScreenWidthInPx(getContext()) - dpToPx(getContext(), 32),
                ::clickBoardHandler
            )
        }
    }

    private fun getContext(): Context {
        return this@HomeActivity
    }

    private fun clickBoardHandler(tag: Int) {
        binding.boardWidget.handleClick(tag,::getSelectableStateOption)
    }

    private fun getSelectableStateOption(optionList : Set<Int>,isFixedWidget : Boolean){
        binding.digitBoxContainerWidget.setSelectableState(optionList,isFixedWidget)
    }

    private fun digitBoxClickHandler(tag : Int) {
        Log.d("klogs", tag.toString())
    }

}