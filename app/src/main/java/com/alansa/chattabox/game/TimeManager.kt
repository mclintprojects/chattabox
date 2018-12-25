package com.alansa.chattabox.game

import android.arch.lifecycle.MutableLiveData
import com.alansa.chattabox.util.Timer

class TimeManager {
    val READY_SECS = 10
    val ANSWER_SECS = 3
    private lateinit var readyTimer: Timer
    private lateinit var answerTimer: Timer

    val currentReadySecs = MutableLiveData<Int>()
    val currentAnswerSecs = MutableLiveData<Int>()

    fun startReadyCountdown() {
        readyTimer = Timer(READY_SECS * 1000, 1000)
        readyTimer.setOnTickListener {
            secs -> currentReadySecs.value = secs
        }
        readyTimer.setOnTimeElapsed { currentReadySecs.value = 0 }
        readyTimer.start()
    }
}