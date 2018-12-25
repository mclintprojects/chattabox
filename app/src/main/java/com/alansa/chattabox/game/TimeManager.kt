package com.alansa.chattabox.game

import android.databinding.ObservableField
import com.alansa.chattabox.util.Timer

class TimeManager {
    private val READY_SECS = 10L
    private val ANSWER_SECS = 5L
    private lateinit var readyTimer: Timer
    private lateinit var answerTimer: Timer

    val currentReadySecs = ObservableField<Int>()
    val currentAnswerSecs = ObservableField<Int>()

    init {
        currentReadySecs.set(10)
        currentReadySecs.set(3)
    }

    fun startReadyCountdown(elapsed: () -> Unit) {
        readyTimer = Timer(READY_SECS * 1000)
        readyTimer.setOnTickListener { secs -> currentReadySecs.set(secs) }
        readyTimer.setOnTimeElapsed {
            elapsed()
            currentReadySecs.set(0)
        }
        readyTimer.start()
    }

    fun startAnswerCountdown(elapsed: () -> Unit) {
        answerTimer = Timer(ANSWER_SECS * 1000)
        answerTimer.setOnTickListener { secs -> currentAnswerSecs.set(secs) }
        answerTimer.setOnTimeElapsed {
            currentAnswerSecs.set(0)
            elapsed()
        }
        answerTimer.start()
    }

    fun reset(){
        currentReadySecs.set(10)
        if(::answerTimer.isInitialized) answerTimer.cancel()
        readyTimer.cancel()
    }
}