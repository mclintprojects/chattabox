package com.alansa.chattabox.game

import android.app.Application
import android.databinding.ObservableField

class PlayerManager(private val scoreManager: ScoreManager) {
    private lateinit var players: MutableList<String>
    private lateinit var playerCompletedListener: (String) -> Unit
    private lateinit var onePlayerLeftListener: () -> Unit
    val currentPlayer = ObservableField<String>("")
    private var currentPlayerIndex = 0
    private var turns = 1

    fun setPlayers(players: MutableList<String>) {
        scoreManager.setPlayers(players)
        this.players = players
    }

    fun setTurns(turns: Int) {
        this.turns = turns
    }

    fun setPlayerCompletedListener(listener: (String) -> Unit) {
        this.playerCompletedListener = listener
    }

    fun setOnePlayerLeftListener(listener: () -> Unit) {
        this.onePlayerLeftListener = listener
    }

    fun awardPoint() {
        scoreManager.awardPoint(players[currentPlayerIndex])
        if (scoreManager.getPoint(players[currentPlayerIndex]) == turns) {
            removeCurrentPlayer()
            if (players.size == 1 && ::onePlayerLeftListener.isInitialized) onePlayerLeftListener()
        }
    }

    fun initialize() {
        currentPlayer.set(players[currentPlayerIndex])
    }

    fun nextPlayer() {
        ++currentPlayerIndex
        if (currentPlayerIndex == players.size) currentPlayerIndex = 0

        currentPlayer.set(players[currentPlayerIndex])
    }

    fun removeCurrentPlayer() {
        if (::playerCompletedListener.isInitialized) playerCompletedListener(players[currentPlayerIndex])
        players.removeAt(currentPlayerIndex)
    }

    fun reset() {
        currentPlayerIndex = 0
        currentPlayer.set("")
        players = mutableListOf()
    }

    fun saveScores(app: Application) = scoreManager.saveScores(app)

    fun getScores(app: Application) = scoreManager.getScores(app)
}