package com.example.testproject.listener

import android.os.SystemClock
import android.view.View

class MultipleClickListener(
    private val defaultInterval: Int = 1000,
    private val onMultipleClick: (view: View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(view: View) {
        if ((SystemClock.elapsedRealtime() - lastTimeClicked) < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onMultipleClick(view)
    }

}