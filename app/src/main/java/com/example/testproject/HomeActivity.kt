package com.example.testproject

import android.content.Context
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.testproject.databinding.ActivityHomeBinding
import com.example.testproject.utils.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private var striked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setForcedLightTheme(resources)
        init()
    }

    private fun init() {
        binding.apply {
            hintView.setOnClickListener(this@HomeActivity)
            eraseView.setOnClickListener(this@HomeActivity)
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
        binding.boardWidget.handleClick(tag, ::getSelectableStateOption)
    }

    private fun getSelectableStateOption(optionList: Set<Int>, isFixedWidget: Boolean) {
        binding.digitBoxContainerWidget.setSelectableState(optionList, isFixedWidget)
    }

    private fun digitBoxClickHandler(tag: Int) {
        binding.boardWidget.setData(tag)
    }

    private fun handleHintClick() {
        striked = !striked
        if (striked) {
            binding.hintView.paintFlags = binding.hintView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.hintView.paintFlags = 0
        }
        binding.digitBoxContainerWidget.toggleHintEnabled()
    }

    private fun handleEraseClick() {
        binding.boardWidget.eraseData(binding.eraseView)
//        if(striked)
//           binding.eraseView.startShaking()
//        else
//            binding.eraseView.startBackgroundAnimation()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.hint_view -> handleHintClick()
            R.id.erase_view -> handleEraseClick()
        }
    }

}