package com.alansa.chattabox.game

class GameManager(private val playerManager: PlayerManager, private val timeManager: TimeManager) {
    fun awardPoint(playerName: String){
        playerManager.awardPoint(playerName)
    }

    fun setPlayers(players: List<String>){
        playerManager.setPlayers(players)
    }

    fun startReadyCountdown(){
        timeManager.startReadyCountdown()
    }
}