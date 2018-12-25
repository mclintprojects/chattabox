package com.alansa.chattabox.game

import android.databinding.ObservableField

class PlayerManager(private val scoreManager: ScoreManager) {
    private lateinit var players: List<String>
    val currentPlayer = ObservableField<String>("")
    private var playerIndex = 0

    fun setPlayers(players: List<String>){
        scoreManager.setPlayers(players)
        this.players = players
    }

    fun awardPoint(playerName: String){
        scoreManager.awardPoint(playerName)
    }

    fun initialize(){
        currentPlayer.set(players[playerIndex])
    }

    fun nextPlayer(){
        ++playerIndex
        if(playerIndex == players.size) playerIndex = 0

        currentPlayer.set(players[playerIndex])
    }

    fun reset(){
        playerIndex = 0
        currentPlayer.set("")
        players = listOf()
    }
}