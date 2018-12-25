package com.alansa.chattabox.game

import android.databinding.ObservableField
import com.alansa.chattabox.util.Timer

class TimeManager {
    val READY_SECS = 10L
    val ANSWER_SECS = 3L
    private lateinit var readyTimer: Timer
    private lateinit var answerTimer: Timer

    val currentReadySecs = ObservableField<Int>()
    val currentAnswerSecs = ObservableField<Int>()
    val readyElapsed = ObservableField<Boolean>()

    init {
        currentReadySecs.set(10)
    }

    fun startReadyCountdown() {
        readyTimer = Timer(READY_SECS * 1000, 1000)
        readyTimer.setOnTickListener { secs ->
            currentReadySecs.set(secs)
        }
        readyTimer.setOnTimeElapsed {
            currentReadySecs.set(0)
            readyElapsed.set(true)
        }
        readyTimer.start()
    }
}