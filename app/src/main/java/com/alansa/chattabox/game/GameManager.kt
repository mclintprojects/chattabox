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

    val scores: List<ScoreViewModel>
        get() = playerManager.getScores(app)

    val playerNames: MutableList<PlayerViewModel>
        get() = playerManager.deserializeSavedPlayerNames(app)

    val showReadyScreen = ObservableField(true)

    val showAnswerTimer = ObservableField(true)

    private lateinit var onePlayerLeftListener: () -> Unit
    private var gameOver = false

    init {
        timeManager.audioManager = audioManager
        playerManager.setPlayerCompletedListener { playerName ->
            audioManager.speak("${playerName}, you're done!")
        }
        playerManager.setOnePlayerLeftListener {
            gameOver = true
            onePlayerLeftListener()
            audioManager.speak("Game over!")
        }
    }

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
        if (!gameOver) {
            audioManager.speak("${playerManager.currentPlayer.get()!!}, get ready!")
            timeManager.startReadyCountdown {
                showReadyScreen.set(false)
                letterManager.chooseNextLetter()
                showAnswerTimer.set(true)
                startAnswerCountdown()
            }
        }
    }

    private fun startAnswerCountdown() {
        if (!gameOver) {
            audioManager.speak("Shout a word that starts with ${letterManager.currentLetter.get()!!}")
            timeManager.startAnswerCountdown {
                showAnswerTimer.set(false)
            }
        }
    }

    fun playNextTurn(awardPoint: Boolean = false) {
        timeManager.cancelAnswerCountdown()
        if (awardPoint) playerManager.awardPoint()
        playerManager.chooseNextPlayer()
        showReadyScreen.set(true)
        showAnswerTimer.set(true)
        startReadyCountdown()
    }

    fun resetGame() {
        gameOver = false
        playerManager.reset()
        timeManager.reset()
        showReadyScreen.set(true)
        showAnswerTimer.set(true)
    }

    fun finishGame() {
        playerManager.saveScores(app)
    }
}