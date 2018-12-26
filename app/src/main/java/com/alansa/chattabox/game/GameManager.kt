package com.alansa.chattabox.game

import android.app.Application
import android.databinding.ObservableField
import com.alansa.chattabox.viewmodels.PlayerViewModel
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

    private lateinit var onePlayerLeftListener: () -> Unit

    init {
        timeManager.audioManager = audioManager
        playerManager.setPlayerCompletedListener { playerName ->
            audioManager.speak("${playerName}, you're done!")
        }
        playerManager.setOnePlayerLeftListener {
            onePlayerLeftListener()
            audioManager.speak("Game over!")
        }
    }

    fun getScores(): List<ScoreViewModel> = playerManager.getScores(app)

    fun getPlayerNames(): MutableList<PlayerViewModel> = playerManager.deserializePlayerNames(app)

    fun setPlayers(players: MutableList<String>) {
        playerManager.setPlayers(players)
        playerManager.initialize()
    }

    fun setTurns(turns: Int) {
        this.playerManager.setTurns(turns)
    }

    fun savePlayerNames() = playerManager.serializePlayerNames(app)

    fun setOnePlayerLeftListener(listener: () -> Unit) {
        this.onePlayerLeftListener = listener
    }

    fun startReadyCountdown() {
        audioManager.speak("${playerManager.currentPlayer.get()!!}, get ready!")
        timeManager.startReadyCountdown {
            showReadyScreen.set(false)
            letterManager.nextLetter()
            showAnswerTimer.set(true)
            startAnswerCountdown()
        }
    }

    private fun startAnswerCountdown() {
        audioManager.speak("Shout a word that starts with ${letterManager.currentLetter.get()!!}")
        timeManager.startAnswerCountdown {
            showAnswerTimer.set(false)
        }
    }

    fun nextTurn(awardPoint: Boolean = false) {
        timeManager.cancelAnswerCountdown()
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