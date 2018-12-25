package com.alansa.chattabox.util

import android.os.CountDownTimer

class Timer(millis: Int, tick: Int) : CountDownTimer(millis.toLong(), tick.toLong()) {
    private lateinit var tickElapsed: (Int) -> Unit
    private lateinit var timeElapsed: () -> Unit
    private var millisUntilFinished = millis

    override fun onFinish() {
        timeElapsed()
    }

    override fun onTick(p0: Long) {
        millisUntilFinished -= 1000
        tickElapsed(millisUntilFinished / 1000)
    }

    fun setOnTickListener(listener: (Int) -> Unit) {
        this.tickElapsed = listener
    }

    fun setOnTimeElapsed(listener: () -> Unit) {
        this.timeElapsed = listener
    }
}