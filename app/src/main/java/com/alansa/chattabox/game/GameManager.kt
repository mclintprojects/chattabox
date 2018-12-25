package com.alansa.chattabox.game

class GameManager(private val playerManager: PlayerManager, private val timeManager: TimeManager) {
    val readySecs
        get() = timeManager.currentReadySecs

    val answerSecs
        get() = timeManager.currentAnswerSecs

    val readyElapsed
        get() = timeManager.readyElapsed

    fun awardPoint(playerName: String) {
        playerManager.awardPoint(playerName)
    }

    fun setPlayers(players: List<String>) {
        playerManager.setPlayers(players)
    }

    fun startReadyCountdown() {
        timeManager.startReadyCountdown()
    }
}