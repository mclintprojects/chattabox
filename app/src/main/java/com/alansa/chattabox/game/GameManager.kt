package com.alansa.chattabox.game

import android.app.Application
import android.databinding.ObservableField
import com.alansa.chattabox.viewmodels.ScoreViewModel

class GameManager(private val app: Application,
                  private val playerManager: PlayerManager,
                  private val timeManager: TimeManager,
                  private val letterManager: LetterManager,
                  private val audioManager: AudioManager) {

    val readySecs
        get() = timeManager.currentReadySecs

    val answerSecs
        get() = timeManager.currentAnswerSecs

    val currentPlayer
        get() = playerManager.currentPlayer

    val currentLetter
        get() = letterManager.currentLetter

    val showReadyScreen = ObservableField(true)

    val showAnswerTimer = ObservableField(true)

    init {
        timeManager.audioManager = audioManager
    }

    fun getScores(): List<ScoreViewModel> = playerManager.getScores(app)

    fun setPlayers(players: List<String>) {
        playerManager.setPlayers(players)
        playerManager.initialize()
    }

    fun startReadyCountdown() {
        timeManager.startReadyCountdown {
            showReadyScreen.set(false)
            letterManager.nextLetter()
            showAnswerTimer.set(true)
            startAnswerCountdown()
        }
    }

    fun startAnswerCountdown() {
        timeManager.startAnswerCountdown {
            showAnswerTimer.set(false)
        }
    }

    fun nextTurn(awardPoint: Boolean = false) {
        if (awardPoint) playerManager.awardPoint()
        playerManager.nextPlayer()
        showReadyScreen.set(true)
        showAnswerTimer.set(true)
        startReadyCountdown()
    }

    fun reset() {
        playerManager.reset()
        timeManager.reset()
        showReadyScreen.set(true)
        showAnswerTimer.set(true)
    }

    fun finish() {
        playerManager.saveScores(app)
    }
}