package com.alansa.chattabox.util

import android.os.CountDownTimer

class Timer(millis: Long, tick: Long) : CountDownTimer(millis, tick) {
    private lateinit var tickElapsed: (Int) -> Unit
    private lateinit var timeElapsed: () -> Unit

    override fun onFinish() {
        timeElapsed()
        cancel()
    }

    override fun onTick(millisUntilFinished: Long) {
        if(::tickElapsed.isInitialized) tickElapsed(millisUntilFinished.toInt() / 1000)
    }

    fun setOnTickListener(listener: (Int) -> Unit) {
        this.tickElapsed = listener
    }

    fun setOnTimeElapsed(listener: () -> Unit) {
        this.timeElapsed = listener
    }
}