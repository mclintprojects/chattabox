package com.alansa.chattabox.game

class PlayerManager(private val scoreManager: ScoreManager) {
    fun setPlayers(players: List<String>){
        scoreManager.setPlayers(players)
    }

    fun awardPoint(playerName: String){
        scoreManager.awardPoint(playerName)
    }
}