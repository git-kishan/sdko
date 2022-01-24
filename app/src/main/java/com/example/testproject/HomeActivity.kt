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
            digitBoxContainerWidget.initView(::digitBoxHandler)
            boardWidget.initView(
                getScreenWidthInPx(getContext()) - dpToPx(getContext(), 32),
                ::clickHandler
            )
        }
    }

    private fun getContext(): Context {
        return this@HomeActivity
    }

    private fun clickHandler(tag: Int) {
        binding.boardWidget.handleClick(tag)
    }

    private fun digitBoxHandler(digitBoxWidget: DigitBoxWidget) {
        Log.d("klogs", digitBoxWidget.getData().toString())
    }

}