package com.alansa.chattabox.game

import android.databinding.ObservableField
import com.alansa.chattabox.util.Timer

class TimeManager {
    private val READY_SECS = 10L
    private val ANSWER_SECS = 5L
    private lateinit var readyTimer: Timer
    private lateinit var answerTimer: Timer
    lateinit var audioManager: AudioManager

    val currentReadySecs = ObservableField<Int>()
    val currentAnswerSecs = ObservableField<Int>()

    fun startReadyCountdown(elapsed: () -> Unit) {
        readyTimer = Timer(READY_SECS * 1000)
        readyTimer.setOnTickListener { secs ->
            if (secs > 0) currentReadySecs.set(secs)
            if (secs in 1..3) audioManager.speak(secs.toString()) else if (secs > 3) audioManager.playTick()
        }
        readyTimer.setOnTimeElapsed { elapsed() }
        readyTimer.start()
    }

    fun startAnswerCountdown(elapsed: () -> Unit) {
        answerTimer = Timer(ANSWER_SECS * 1000)
        answerTimer.setOnTickListener { secs ->
            if (secs > 0) {
                currentAnswerSecs.set(secs)
                audioManager.playTick()
            }
        }
        answerTimer.setOnTimeElapsed {
            elapsed()
            audioManager.speak("Time's up!")
        }
        answerTimer.start()
    }

    fun cancelAnswerCountdown() = answerTimer.cancel()

    fun reset() {
        currentReadySecs.set(10)
        if (::answerTimer.isInitialized) answerTimer.cancel()
        readyTimer.cancel()
    }
}