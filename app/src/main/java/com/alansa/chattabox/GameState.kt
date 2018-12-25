package com.alansa.chattabox

import com.alansa.chattabox.game.*

class GameState {
    companion object {
        lateinit var gameManager: GameManager

        fun initialize() {
            gameManager = GameManager(PlayerManager(ScoreManager()), TimeManager(), LetterManager())
        }

        fun clear() = gameManager.reset()
    }
}