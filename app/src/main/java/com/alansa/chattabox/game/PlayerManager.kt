package com.alansa.chattabox.game

import android.app.Application
import android.databinding.ObservableField

class PlayerManager(private val scoreManager: ScoreManager) {
    private lateinit var players: List<String>
    val currentPlayer = ObservableField<String>("")
    private var currentPlayerIndex = 0

    fun setPlayers(players: List<String>) {
        scoreManager.setPlayers(players)
        this.players = players
    }

    fun awardPoint() {
        scoreManager.awardPoint(players[currentPlayerIndex])
    }

    fun initialize() {
        currentPlayer.set(players[currentPlayerIndex])
    }

    fun nextPlayer() {
        ++currentPlayerIndex
        if (currentPlayerIndex == players.size) currentPlayerIndex = 0

        currentPlayer.set(players[currentPlayerIndex])
    }

    fun reset() {
        currentPlayerIndex = 0
        currentPlayer.set("")
        players = listOf()
    }

    fun saveScores(app: Application) = scoreManager.saveScores(app)

    fun getScores(app: Application) = scoreManager.getScores(app)
}